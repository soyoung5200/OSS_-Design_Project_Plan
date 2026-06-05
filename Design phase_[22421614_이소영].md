# Design phase

## 1. Introduction

본 문서는 가계부 관리 종합 서비스의 세 번째 개발 단계인 Design 단계의 산출물이다. Conceptualization 및 Analysis 단계에서 도출된 요구사항과 Use Case를 기반으로, 실제 구현에 직접 관여하는 모든 요소의 윤곽을 확정하고 구체적인 설계 내용을 다룬다. 본 문서에서 정의하는 Class Diagram, Sequence Diagram, State Machine Diagram 및 Implementation Requirements는 실제 소스 코드 구현 시 준거가 되는 설계 명세이다.

**시스템의 주요 설계 원칙**

- **사용자 금융 데이터의 보안성 우선**: 은행 API 인증 토큰 암호화 및 HTTPS 통신 강제
- **관심사 분리(Separation of Concerns)**: 도메인 클래스와 UI, 알림, 분석 로직을 명확히 분리
- **확장성 확보**: 다수 금융기관 연동 및 신규 기능 추가에 유연하게 대응 가능한 구조
- **최소 터치 원칙**: 사용자 조작을 최소화하여 지속적인 사용을 유도하는 직관적 UI 흐름

---

## 2. Class Diagram

아래는 본 시스템의 핵심 도메인 클래스들에 대한 다이어그램과 설명이다.

<img width="3504" height="2312" alt="class_diagram" src="https://github.com/user-attachments/assets/f1663921-1b78-4bc8-b1d5-3cfe491decdf" />


### 1) User

**(1) Attributes**

| `-userId: String` | 사용자 고유 식별자 |
|---|---|
| `-email: String` | 이메일 주소 |
| `-password: String` | 암호화된 비밀번호 |
| `-createdAt: Date` | 가입 일시 |

**(2) Methods**

| `+login(email: String, password: String): boolean` |
|---|
| `+logout(): void` |
| `+updateProfile(info: UserInfo): void` |
| `+getBudget(): Budget` |

---

### 2) AccountLink

**(1) Attributes**

| `-linkId: String` | 연동 고유 ID |
|---|---|
| `-bankCode: String` | 금융기관 코드 |
| `-accountNo: String` | 계좌번호 (마스킹) |
| `-accessToken: String` | API 인증 토큰 |
| `-lastSyncAt: Date` | 마지막 동기화 시점 |

**(2) Methods**

| `+sync(): List<Transaction>` |
|---|
| `+revoke(): void` |
| `+isActive(): boolean` |

---

### 3) Transaction

**(1) Attributes**

| `-txId: String` | 거래 고유 ID |
|---|---|
| `-amount: int` | 거래 금액 |
| `-date: Date` | 거래 일시 |
| `-merchant: String` | 가맹점명 |
| `-categoryId: String` | 카테고리 ID |
| `-themeId: String` | 상위 테마 ID (nullable) |
| `-memo: String` | 사용자 메모 |
| `-isEdited: boolean` | 편집 여부 |

**(2) Methods**

| `+edit(amount: int, categoryId: String, memo: String): void` |
|---|
| `+delete(): void` |
| `+applyDutchPay(headCount: int): void` |
| `+assignTheme(themeId: String): void` |

---

### 4) ThemeGroup

**(1) Attributes**

| `-themeId: String` | 테마 고유 ID |
|---|---|
| `-userId: String` | 소유 사용자 ID |
| `-name: String` | 테마 이름 |
| `-targetBudget: int` | 테마 목표 예산 |
| `-createdAt: Date` | 생성 일시 |

**(2) Methods**

| `+getTotalSpent(): int` |
|---|
| `+getTransactions(): List<Transaction>` |
| `+rename(name: String): void` |
| `+delete(): void` |

---

### 5) Budget

**(1) Attributes**

| `-budgetId: String` | 예산 고유 ID |
|---|---|
| `-userId: String` | 사용자 ID |
| `-monthlyIncome: int` | 월 수입 |
| `-fixedExpense: int` | 고정 지출 합계 |
| `-warningThreshold: float` | 경고 임계치 (0.0~1.0) |
| `-yearMonth: String` | 대상 연월 (YYYY-MM) |

**(2) Methods**

| `+getAvailableAmount(): int` |
|---|
| `+getCurrentSpent(): int` |
| `+checkThreshold(): boolean` |

---

### 6) DutchPay

**(1) Attributes**

| `-dutchId: String` | 더치페이 고유 ID |
|---|---|
| `-txId: String` | 원본 거래 ID |
| `-totalAmount: int` | 총 결제 금액 |
| `-headCount: int` | 참여 인원수 |
| `-myShare: int` | 개인 부담금 |

**(2) Methods**

| `+calculate(totalAmount: int, headCount: int): int` |
|---|
| `+applyToTransaction(): void` |

---

### 7) FixedSchedule

**(1) Attributes**

| `-scheduleId: String` | 일정 고유 ID |
|---|---|
| `-userId: String` | 사용자 ID |
| `-title: String` | 일정 이름 (예: 월급) |
| `-dayOfMonth: int` | 매월 해당 일 |
| `-amount: int` | 예상 금액 |
| `-type: String` | `'INCOME'` \| `'EXPENSE'` |

**(2) Methods**

| `+generateNotification(): Notification` |
|---|
| `+update(title: String, dayOfMonth: int): void` |
| `+delete(): void` |

---

### 8) Notification

**(1) Attributes**

| `-notiId: String` | 알림 고유 ID |
|---|---|
| `-userId: String` | 대상 사용자 ID |
| `-type: String` | `'WARNING'` \| `'REMINDER'` |
| `-message: String` | 알림 메시지 |
| `-isRead: boolean` | 읽음 여부 |
| `-createdAt: Date` | 생성 일시 |

**(2) Methods**

| `+send(): void` |
|---|
| `+markAsRead(): void` |

---

### 9) ConsumptionAnalysis

**(1) Attributes**

| `-analysisId: String` | 분석 고유 ID |
|---|---|
| `-userId: String` | 사용자 ID |
| `-yearMonth: String` | 분석 대상 연월 |
| `-categoryStats: Map<String,int>` | 카테고리별 지출 |
| `-monthOverMonth: float` | 전월 대비 증감률 |

**(2) Methods**

| `+generate(userId: String, yearMonth: String): ConsumptionAnalysis` |
|---|
| `+getCategoryChart(): ChartData` |
| `+getSummaryText(): String` |

---

### 10) Category

**(1) Attributes**

| `-categoryId: String` | 카테고리 ID |
|---|---|
| `-name: String` | 카테고리명 (예: 식비) |
| `-iconUrl: String` | 아이콘 경로 |
| `-color: String` | 표시 색상 코드 |

**(2) Methods**

| `+getAll(): List<Category>` |
|---|
| `+findById(categoryId: String): Category` |

---

## 3. Sequence Diagram

아래에 나오는 그림들은 Analysis 단계에서 정의한 Use Case들을 Sequence Diagram으로 표현한 것이다.

---

### 1) 금융 데이터 연동

사용자가 '동기화' 버튼을 누르면 `AccountLink.sync()`가 호출된다. 시스템은 은행 API에 인증 토큰과 함께 거래 내역을 요청하고, 수신된 데이터를 카테고리별로 분류하여 Transaction으로 저장한다. API 오류 발생 시 실패 메시지와 함께 수동 입력 안내를 제공한다.

<img width="2385" height="1335" alt="sd1_sync" src="https://github.com/user-attachments/assets/d2759521-dc88-4c22-b7cb-5eccc91570f2" />

| **Step** | **From** | **To / Action** | **Message / Return** |
|---|---|---|---|
| 1 | User → UI | 동기화 버튼 클릭 | |
| 2 | UI → AccountLink | `sync()` 호출 | |
| 3 | AccountLink → BankAPI | 인증 토큰 + 거래 내역 요청 | |
| 4 | BankAPI → AccountLink | 거래 데이터 반환 | |
| 5 | AccountLink → Transaction | 카테고리 분류 후 저장 | |
| 6 | Transaction → UI | 완료 메시지 반환 | |

---

### 2) 상위 테마 그룹화

사용자가 복수의 거래 내역을 선택 후 테마명을 입력하면 ThemeGroup이 생성되고 각 Transaction에 themeId가 할당된다.

<img width="2085" height="1185" alt="sd2_theme" src="https://github.com/user-attachments/assets/542357aa-2b21-4669-861a-2b1d30310576" />

| **Step** | **From** | **To / Action** | **Message / Return** |
|---|---|---|---|
| 1 | User → UI | 거래 내역 다중 선택 + 테마명 입력 | |
| 2 | UI → ThemeGroup | `create(name, transactions)` | |
| 3 | ThemeGroup → Transaction | `assignTheme(themeId)` 반복 호출 | |
| 4 | ThemeGroup → UI | 테마별 합계 반환 및 화면 갱신 | |

---

### 3) 더치페이 지출 계산

지출 상세 화면에서 더치페이를 실행하고 인원수를 입력하면 `DutchPay.calculate()`가 개인 부담금을 산출하고, 원본 Transaction 금액을 수정한다.

<img width="2085" height="1335" alt="sd3_dutch" src="https://github.com/user-attachments/assets/e73503fe-f32c-4610-8556-1ed8a595d627" />

| **Step** | **From** | **To / Action** | **Message / Return** |
|---|---|---|---|
| 1 | User → UI | 더치페이 계산 실행 + 인원수 입력 | |
| 2 | UI → DutchPay | `calculate(totalAmount, headCount)` | |
| 3 | DutchPay → DutchPay | `myShare = totalAmount / headCount` 연산 | |
| 4 | DutchPay → Transaction | `applyToTransaction()` → amount 업데이트 | |
| 5 | Transaction → UI | 갱신된 금액 표시 | |

---

### 4) 가용 금액 경고 알림

새로운 거래 등록 시 `Budget.checkThreshold()`가 실행된다. 누적 지출이 임계치를 초과하면 Notification을 생성하고 푸시 알림을 발송한다.

<img width="2085" height="1335" alt="sd4_warning" src="https://github.com/user-attachments/assets/9888157b-068e-4d58-a8f8-0cd781905f09" />

| **Step** | **From** | **To / Action** | **Message / Return** |
|---|---|---|---|
| 1 | Transaction | 저장 이벤트 발생 | |
| 2 | Budget → Budget | `getCurrentSpent()` 재계산 | |
| 3 | Budget → Budget | `checkThreshold()` — 임계치 초과 확인 | |
| 4 | Budget → Notification | `create(type='WARNING', message)` | |
| 5 | Notification → PushService | `send()` — 사용자 기기에 알림 전송 | |

---

### 5) 고정 소득/지출 확인 알림

스케줄러가 매일 자정 FixedSchedule을 조회하여 당일 일정이 있는 사용자에게 리마인드 알림을 발송한다.

<img width="2385" height="1335" alt="sd5_reminder" src="https://github.com/user-attachments/assets/fc19c248-baa8-4874-b63b-bf166da609d3" />

| **Step** | **From** | **To / Action** | **Message / Return** |
|---|---|---|---|
| 1 | Scheduler → FixedSchedule | 당일 `dayOfMonth` 일치 항목 조회 | |
| 2 | FixedSchedule → Notification | `generateNotification()` 호출 | |
| 3 | Notification → PushService | `send()` — 맞춤 메시지 발송 | |
| 4 | User → UI | 알림 클릭 → 입출금 확인 화면 이동 | |

---

### 6) 소비 습관 분석

사용자가 분석 탭을 선택하면 `ConsumptionAnalysis.generate()`가 해당 월 거래 데이터를 집계하고 카테고리별 차트와 전월 대비 코멘트를 생성한다.

<img width="2085" height="1335" alt="sd6_analysis" src="https://github.com/user-attachments/assets/9bae8512-e5ed-4e82-8fb4-9e7cbc366826" />

| **Step** | **From** | **To / Action** | **Message / Return** |
|---|---|---|---|
| 1 | User → UI | 분석 탭 선택 | |
| 2 | UI → ConsumptionAnalysis | `generate(userId, yearMonth)` 호출 | |
| 3 | ConsumptionAnalysis → Transaction | 해당 월 거래 데이터 추출 | |
| 4 | ConsumptionAnalysis | 카테고리별 통계 + 전월 대비 증감 계산 | |
| 5 | ConsumptionAnalysis → UI | `getCategoryChart()` + `getSummaryText()` 반환 | |

---

### 7) 연동 내역 편집 및 삭제

거래 상세 화면에서 수정 또는 삭제를 선택하면 `Transaction.edit()` 또는 `delete()`가 호출되어 DB가 갱신되고 통계가 즉시 반영된다.

<img width="2385" height="1335" alt="sd7_edit" src="https://github.com/user-attachments/assets/77f851b5-25a4-4a70-88b2-799c64970d61" /><img width="3585" height="2385" alt="state_machine" src="https://github.com/user-attachments/assets/6fc6ad6a-ed7a-4ed9-a537-c3c9cfa95c08" />


| **Step** | **From** | **To / Action** | **Message / Return** |
|---|---|---|---|
| 1 | User → UI | 거래 항목 선택 → 수정/삭제 선택 | |
| 2 | UI → Transaction | `edit(amount, categoryId, memo)` 또는 `delete()` | |
| 3 | Transaction → DB | 레코드 UPDATE 또는 DELETE | |
| 4 | DB → ConsumptionAnalysis | 통계 재계산 트리거 | |
| 5 | ConsumptionAnalysis → UI | 변경된 내역 화면 갱신 | |

---

## 4. State Machine Diagram

아래는 가계부 관리 종합 서비스 전체 시스템의 State Machine Diagram이다. 시스템은 '앱 시작'에서 초기화되어 사용자의 행동 및 스케줄러 이벤트에 따라 상태가 전이되며, '앱 종료' 상태에서 종료된다.

<img width="3585" height="2385" alt="state_machine" src="https://github.com/user-attachments/assets/17d0a123-edcb-4d0a-b065-e96c27d8874c" />


아래는 각 State에 대한 설명이다.

| **State** | **Explanation** |
|---|---|
| 앱 시작 | 앱이 실행된 초기 상태. 로그인 화면을 표시한다. |
| 로그인 | 이메일/비밀번호를 입력하여 인증을 시도하는 상태. |
| 메인 대시보드 | 로그인 성공 후 진입. 가용 예산 게이지 및 최근 거래 내역 요약을 표시. |
| 금융 데이터 동기화 중 | 은행 API와 통신하여 거래 내역을 수집하는 상태. |
| 거래 내역 목록 | 수집된 거래 내역의 전체 목록을 표시하는 상태. |
| 거래 내역 상세/편집 | 특정 거래를 선택하여 카테고리·금액·메모를 수정하는 상태. |
| 더치페이 계산 중 | 공동 결제 내역에 대해 인원수를 입력하고 개인 부담금을 계산하는 상태. |
| 테마 그룹 관리 | 상위 테마를 생성하거나 기존 테마에 거래를 추가/제거하는 상태. |
| 예산 설정 | 월 수입, 고정 지출, 경고 임계치를 설정하는 상태. |
| 경고 알림 발송 중 | 누적 지출이 임계치를 초과하여 알림 생성 및 발송을 처리하는 상태. |
| 소비 습관 분석 | 카테고리별 통계 및 전월 대비 리포트를 렌더링하는 상태. |
| 고정 일정 관리 | 월급일·공과금일 등 정기 수입/지출 일정을 등록·수정하는 상태. |
| 리마인드 알림 발송 중 | 스케줄러가 해당 일정 사용자에게 푸시 알림을 전송하는 상태. |
| 앱 종료 | 사용자가 로그아웃하거나 앱을 종료한 상태. |

---

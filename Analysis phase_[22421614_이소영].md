#Analysis Document

## 1. Introduction

### 1) Summary
현대인들은 신용카드, 체크카드뿐 아니라 삼성페이, 카카오페이와 같은 간편결제 서비스를 통해 매우 편리하게 소비 활동을 하고 있있다. 그러나 결제 과정이 지나치게 간단해지면서 사용자는 자신이 얼마를, 어디에, 왜 사용했는지를 체계적으로 파악하기 어려워졌다. 그 결과 소득이 있음에도 불구하고 지출 흐름을 명확히 인지하지 못해 경제적 어려움을 겪는 사례가 증가하고 있다. 이러한 문제를 해결하기 위해 본 프로젝트에서는 단순한 수기 가계부 기능을 넘어, 금융 데이터 연동, 소비 패턴 분석, 더치페이 계산, 예산 초과 경고, 고정 수입 및 지출 확인 알림 등 다양한 기능을 제공하는 가계부 관리 종합 서비스를 제공한다. 본 시스템은 사용자의 소비를 자동으로 기록하고 분석하여 과소비를 방지하고, 보다 계획적인 자산 관리를 가능하게 하는 것을 목표로 한다.
본 문서는 Conceptualization Document에 이어지는 Analysis 단계의 문서로서, 시스템의 기능을 Use case 관점에서 분석하고 상세 시나리오를 정의한다.

### 2) Features of the Service
본 시스템은 사용자의 금융 데이터를 은행 및 카드사와 연동하여 자동으로 수집하고, 수집된 거래 내역을 기반으로 소비를 분석한다. 또한 사용자가 특정 지출들을 하나의 상위 테마로 묶어 관리할 수 있도록 하며, 더치페이 계산 기능을 통해 실제 개인 부담 금액만 반영할 수 있다. 추가적으로 사용자가 월 수입과 고정 지출을 설정하면 가용 금액을 계산하고, 예산 초과 위험 시 경고 알림을 제공한다. 월급일이나 정기 수입일에는 입금 여부를 확인하도록 알림을 제공하며, 자동 수집된 거래 내역 중 불필요한 항목은 사용자가 직접 수정 또는 삭제할 수 있다.

### 3) Goals
이번 Analysis 보고서에서는 제안한 가계부 관리 종합 서비스의 기능들을 체계적으로 정의하기 위해 Use case diagram 및 Use case description을 도출한다. 각 기능의 성공 경로와 예외 상황을 표준화된 양식으로 기술하여 시스템 설계의 기초를 마련하는 것을 목표로 한다.

---

## 2. Use case analysis

### 2.1 Use case diagram
본 시스템은 사용자(User)와 외부 금융기관(Bank/Card System) 간의 상호작용을 중심으로 하며, 시스템 내부 스케줄러(Scheduler)가 자동화된 알림 및 분석을 수행한다.
<img width="659" height="514" alt="UseCaseDiagram" src="https://github.com/user-attachments/assets/50abae6a-fa0f-47f8-bb90-2cc0e102f84e" />

### 2.2 Use case description

#### Use Case #1 : 금융 데이터 연동

**1) GENERAL CHARACTERISTICS**
| | |
| :--- | :--- |
| **Use Case Name** | 금융 데이터 연동 |
| **Summary** | 사용자의 동의하에 은행 및 카드사와 API로 연동하여 입출금 및 결제 내역을 자동으로 가져오는 기능 |
| **Scope** | 가계부 관리 종합 서비스 |
| **Level** | User Goal |
| **Author** | |
| **Last Update** | |
| **Status** | |
| **Primary Actor** | User |
| **Preconditions** | 사용자가 시스템에 로그인한 상태여야 하며, 외부 금융기관 연동에 사전 동의 및 인증을 완료해야 한다. |
| **Trigger** | 사용자가 '금융 데이터 동기화'를 실행하거나 백그라운드 자동 갱신 주기가 도래했을 때 |
| **Success Post Condition** | 거래 내역이 시스템 DB에 저장되고 최신 내역으로 업데이트된다. |
| **Failed Post Condition** | 거래 내역 동기화에 실패하며 기존 데이터 상태를 유지한다. |

**2) MAIN SUCCESS SCENARIO**
| Step | Action |
| :--- | :--- |
| 1 | 사용자가 금융 데이터 연동 메뉴를 선택한다. |
| 2 | 시스템은 사용자의 인증 및 동의 상태를 확인한다. |
| 3 | 시스템은 은행/카드사 API를 호출하여 거래 내역을 요청한다. |
| 4 | 외부 시스템으로부터 거래 데이터를 수신한다. |
| 5 | 수신한 데이터를 카테고리별로 자동 분류하여 DB에 저장한다. |
| 6 | 최신 거래 내역 업데이트 완료 메시지를 표시한다. |

**3) EXTENSION SCENARIOS**
| Step | Branching Action |
| :--- | :--- |
| 3a | **API 통신 장애 발생 시:** 시스템은 통신 실패 메시지를 출력하고 수동 입력을 안내한다. |
| 5a | **중복 데이터 발견 시:** 기존 내역과 대조하여 중복 저장을 방지한다. |

**4) RELATED INFORMATION**
| | |
| :--- | :--- |
| **Performance** | API 호출 및 데이터 처리 완료까지 5초 이내 |
| **Frequency** | 앱 접속 시 1회 및 주기적 자동 수행 |
| **Concurrency** | 다중 사용자 동시 접속 시 시스템 지연 방지 처리 필요 |
| **Due Date** | Release 1.0 |

---

#### Use Case #2 : 상위 테마 그룹화

**1) GENERAL CHARACTERISTICS**
| | |
| :--- | :--- |
| **Use Case Name** | 상위 테마 그룹화 |
| **Summary** | 개별 지출 내역을 목적에 따라 하나의 상위 테마로 묶어 관리하는 기능 |
| **Scope** | 가계부 관리 종합 서비스 |
| **Level** | User Goal |
| **Author** | |
| **Last Update** | |
| **Status** | |
| **Primary Actor** | User |
| **Preconditions** | 시스템에 하나 이상의 거래 내역이 존재해야 한다. |
| **Trigger** | 사용자가 지출 항목들을 선택한 뒤 '테마 지정'을 클릭할 때 |
| **Success Post Condition** | 선택된 내역들에 테마 정보가 할당되어 통합 조회가 가능해진다. |
| **Failed Post Condition** | 그룹화가 적용되지 않고 기존 상태를 유지한다. |

**2) MAIN SUCCESS SCENARIO**
| Step | Action |
| :--- | :--- |
| 1 | 거래 내역 목록에서 그룹화할 항목들을 다중 선택한다. |
| 2 | '상위 테마로 묶기' 기능을 실행한다. |
| 3 | 테마 이름을 입력하거나 기존 테마를 선택한다. |
| 4 | 선택된 항목들의 레코드에 해당 테마 정보를 업데이트한다. |
| 5 | 테마별 총 지출 합계를 계산하여 화면에 표시한다. |

**3) EXTENSION SCENARIOS**
| Step | Branching Action |
| :--- | :--- |
| 3a | **테마 명칭 미입력 시:** 테마명 입력을 요청하는 경고 메시지를 표시한다. |

**4) RELATED INFORMATION**
| | |
| :--- | :--- |
| **Performance** | DB 업데이트 및 UI 갱신까지 3초 이내 |
| **Frequency** | 사용자의 필요에 따라 수시 발생 |
| **Concurrency** | 단일 사용자 세션 내에서 처리 |
| **Due Date** | Release 1.0 |

---

#### Use Case #3 : 더치페이 지출 계산

**1) GENERAL CHARACTERISTICS**
| | |
| :--- | :--- |
| **Use Case Name** | 더치페이 지출 계산 |
| **Summary** | 공동 결제 내역에서 인원수대로 나눈 실제 부담 금액만 지출로 반영하는 기능 |
| **Scope** | 가계부 관리 종합 서비스 |
| **Level** | Sub-function |
| **Author** | |
| **Last Update** | |
| **Status** | |
| **Primary Actor** | User |
| **Preconditions** | 분할이 필요한 지출 내역이 기록되어 있어야 한다. |
| **Trigger** | 지출 상세 화면에서 '더치페이 계산'을 실행할 때 |
| **Success Post Condition** | 실제 부담 금액이 계산되어 지출 통계에 반영된다. |
| **Failed Post Condition** | 원본 금액이 유지되며 통계에 변화가 없다. |

**2) MAIN SUCCESS SCENARIO**
| Step | Action |
| :--- | :--- |
| 1 | 분할할 지출 내역의 상세 정보를 조회한다. |
| 2 | 더치페이 계산 기능을 실행한다. |
| 3 | 총 참여 인원수를 입력한다. |
| 4 | 시스템은 인원수별 부담 금액을 산출한다. |
| 5 | 산출된 금액을 사용자의 실제 지출액으로 저장한다. |
| 6 | 변경된 내역을 화면에 갱신한다. |

**3) EXTENSION SCENARIOS**
| Step | Branching Action |
| :--- | :--- |
| 3a | **잘못된 인원수 입력 시:** 유효한 인원수를 입력하도록 안내 메시지를 표시한다. |

**4) RELATED INFORMATION**
| | |
| :--- | :--- |
| **Performance** | 계산 및 반영까지 2초 이내 |
| **Frequency** | 공동 지출 발생 시마다 수행 |
| **Concurrency** | 병행성 충돌 가능성 낮음 |
| **Due Date** | Release 1.0 |

---

#### Use Case #4 : 가용 금액 경고 알림

**1) GENERAL CHARACTERISTICS**
| | |
| :--- | :--- |
| **Use Case Name** | 가용 금액 경고 알림 |
| **Summary** | 목표 예산 대비 지출 비율이 설정치를 넘으면 경고를 제공하는 기능 |
| **Scope** | 가계부 관리 종합 서비스 |
| **Level** | System Goal |
| **Author** | |
| **Last Update** | |
| **Status** | |
| **Primary Actor** | System (Scheduler) |
| **Preconditions** | 사용자가 목표 예산을 사전에 설정해야 한다. |
| **Trigger** | 누적 지출액이 설정된 임계치를 초과하는 시점 |
| **Success Post Condition** | 사용자에게 예산 위험 알림이 발송되고 로그에 기록된다. |
| **Failed Post Condition** | 알림이 생성되지 않거나 발송에 실패한다. |

**2) MAIN SUCCESS SCENARIO**
| Step | Action |
| :--- | :--- |
| 1 | 새로운 지출 내역이 등록된다. |
| 2 | 현재까지의 해당 월 누적 지출액을 합산한다. |
| 3 | 설정된 목표 예산 및 경고 비율과 비교한다. |
| 4 | 임계치 초과 시 경고 메시지를 생성한다. |
| 5 | 사용자 기기로 푸시 알림을 발송한다. |

**3) EXTENSION SCENARIOS**
| Step | Branching Action |
| :--- | :--- |
| 5a | **알림 권한 거부 시:** 앱 실행 시 대시보드 상단에 시각적 경고 배너를 노출한다. |

**4) RELATED INFORMATION**
| | |
| :--- | :--- |
| **Performance** | 연산 및 알림 발송까지 1초 이내 |
| **Frequency** | 설정 비율 도달 시마다 발생 |
| **Concurrency** | 백그라운드 스케줄러를 통한 개별 처리 |
| **Due Date** | Release 1.5 |

---

#### Use Case #5 : 고정 소득 및 지출 확인 알림

**1) GENERAL CHARACTERISTICS**
| | |
| :--- | :--- |
| **Use Case Name** | 고정 소득 및 지출 확인 알림 |
| **Summary** | 지정한 정기 입출금일에 맞춰 내역 확인을 유도하는 리마인드 기능 |
| **Scope** | 가계부 관리 종합 서비스 |
| **Level** | System Goal |
| **Author** | |
| **Last Update** | |
| **Status** | |
| **Primary Actor** | System (Scheduler) |
| **Preconditions** | 정기적인 소득 및 지출 일정이 등록되어 있어야 한다. |
| **Trigger** | 등록된 일정의 날짜 및 시간에 도달했을 때 |
| **Success Post Condition** | 리마인드 알림이 발송되고 관련 내역 조회 화면으로 연결된다. |
| **Failed Post Condition** | 알림이 생성되지 않는다. |

**2) MAIN SUCCESS SCENARIO**
| Step | Action |
| :--- | :--- |
| 1 | 스케줄러가 당일 일정을 확인한다. |
| 2 | 등록된 고정 일정 데이터와 현재 날짜를 대조한다. |
| 3 | 해당 사용자를 위한 맞춤 알림 메시지를 생성한다. |
| 4 | 푸시 알림을 전송한다. |
| 5 | 알림 클릭 시 입출금 확인 화면으로 이동시킨다. |

**3) EXTENSION SCENARIOS**
| Step | Branching Action |
| :--- | :--- |
| 5a | **연동 내역 미수신 시:** 내역 없음 안내와 함께 수동 등록 메뉴를 제공한다. |

**4) RELATED INFORMATION**
| | |
| :--- | :--- |
| **Performance** | 스케줄링 배치 작업 완료까지 5분 이내 |
| **Frequency** | 설정된 주기(매달 등)마다 발생 |
| **Concurrency** | 대량 알림 발송을 위한 큐 처리 필요 |
| **Due Date** | Release 1.5 |

---

#### Use Case #6 : 소비 습관 분석

**1) GENERAL CHARACTERISTICS**
| | |
| :--- | :--- |
| **Use Case Name** | 소비 습관 분석 |
| **Summary** | 누적 데이터를 기반으로 소비 패턴을 시각화하여 리포트를 제공하는 기능 |
| **Scope** | 가계부 관리 종합 서비스 |
| **Level** | User Goal |
| **Author** | |
| **Last Update** | |
| **Status** | |
| **Primary Actor** | System (Report Generator) |
| **Preconditions** | 분석에 필요한 일정량 이상의 거래 데이터가 존재해야 한다. |
| **Trigger** | 사용자가 분석 탭을 선택하거나 정기 보고서 생성 시점이 되었을 때 |
| **Success Post Condition** | 분석 리포트와 시각화 차트가 화면에 출력된다. |
| **Failed Post Condition** | 데이터 분석 오류 메시지를 표시한다. |

**2) MAIN SUCCESS SCENARIO**
| Step | Action |
| :--- | :--- |
| 1 | 지정 기간의 거래 데이터를 DB에서 추출한다. |
| 2 | 카테고리별 지출 통계를 계산한다. |
| 3 | 이전 기간 대비 소비 증감률을 분석한다. |
| 4 | 분석 결과를 차트와 그래프로 렌더링한다. |
| 5 | 종합적인 분석 요약 문구를 출력한다. |

**3) EXTENSION SCENARIOS**
| Step | Branching Action |
| :--- | :--- |
| 1a | **데이터 부족 시:** 분석 불가 사유와 함께 데이터 축적을 권장하는 메시지를 출력한다. |

**4) RELATED INFORMATION**
| | |
| :--- | :--- |
| **Performance** | 통계 산출 및 시각화까지 5초 이내 |
| **Frequency** | 사용자 요청 시 실시간 또는 정기적 생성 |
| **Concurrency** | 복잡한 쿼리에 대한 DB 최적화 필요 |
| **Due Date** | Release 2.0 |

---

#### Use Case #7 : 연동 내역 편집 및 삭제

**1) GENERAL CHARACTERISTICS**
| | |
| :--- | :--- |
| **Use Case Name** | 연동 내역 편집 및 삭제 |
| **Summary** | 자동 수집된 내역의 카테고리나 금액을 수정하거나 불필요한 내역을 삭제하는 기능 |
| **Scope** | 가계부 관리 종합 서비스 |
| **Level** | Sub-function |
| **Author** | |
| **Last Update** | |
| **Status** | |
| **Primary Actor** | User |
| **Preconditions** | 편집할 대상 거래 내역이 시스템에 존재해야 한다. |
| **Trigger** | 내역 상세 화면에서 수정 또는 삭제 버튼을 누를 때 |
| **Success Post Condition** | DB의 거래 정보가 갱신되어 통계에 즉시 반영된다. |
| **Failed Post Condition** | 변경 사항이 저장되지 않고 원본 데이터가 유지된다. |

**2) MAIN SUCCESS SCENARIO**
| Step | Action |
| :--- | :--- |
| 1 | 편집할 거래 내역을 선택하여 상세 창을 활성화한다. |
| 2 | 카테고리, 금액, 메모 등을 수정하거나 삭제를 선택한다. |
| 3 | 입력 정보의 유효성을 검사한다. |
| 4 | DB의 트랜잭션 레코드를 업데이트하거나 삭제 처리한다. |
| 5 | 변경된 목록 화면을 사용자에게 보여준다. |

**3) EXTENSION SCENARIOS**
| Step | Branching Action |
| :--- | :--- |
| 3a | **필수 정보 누락 시:** 필수 항목 입력을 요청하며 저장을 차단한다. |

**4) RELATED INFORMATION**
| | |
| :--- | :--- |
| **Performance** | 반영 완료까지 3초 이내 |
| **Frequency** | 오류 정정 등을 위해 빈번히 발생 |
| **Concurrency** | 분석 데이터와의 실시간 동기화 필요 |
| **Due Date** | Release 1.0 |

---

## 3. Domain analysis

본 시스템의 핵심 비즈니스 로직을 수행하기 위해 식별된 주요 도메인 클래스들을 정의한다. 각 클래스는 시스템의 상태 정보를 저장하거나 기능을 수행하는 주체가 된다.

| | |
| :--- | :--- |
| **User** | 시스템을 이용하는 개인 사용자의 프로필 정보(ID, 비밀번호, 이메일) 및 개인 설정을 관리한다. |
| **AccountLink** | 사용자의 금융기관(은행, 카드사) 계좌 연동 상태, 인증 토큰, 마지막 동기화 시점 등을 관리한다. |
| **Transaction** | 금융기관으로부터 수집된 개별 거래 내역으로, 금액, 날짜, 가맹점명, 카테고리, 테마 ID 등의 속성을 가진다. |
| **ThemeGroup** | 사용자가 특정 목적(예: 여행, 행사)을 위해 여러 거래 내역을 하나로 묶은 상위 관리 단위이다. |
| **Budget** | 사용자가 설정한 월간 수입, 목표 예산, 가용 금액 및 알림을 위한 경고 임계치 정보를 포함한다. |
| **DutchPay** | 공동 결제 내역에 대해 참여 인원수와 분할 계산된 실제 개인 부담 금액을 계산하고 저장한다. |
| **FixedSchedule** | 월급일, 공과금 납부일 등 매달 반복되는 정기적인 수입 및 지출 일정을 저장하고 관리한다. |
| **Notification** | 예산 초과 경고, 고정 소득/지출 리마인드 등 시스템이 생성하여 사용자에게 전달한 알림 내역이다. |
| **ConsumptionAnalysis** | 수집된 거래 데이터를 기반으로 소비 패턴, 전월 대비 지출 변화 등을 통계적으로 분석한 결과 데이터를 관리한다. |
| **Category** | 식비, 교통비, 쇼핑 등 지출 내역을 분류하기 위한 기본 체계와 관련 아이콘 정보를 담고 있다. |

---

## 4. User Interface prototype

### 4.1 메인 대시보드 (Main Dashboard)
<img width="421" height="570" alt="image" src="https://github.com/user-attachments/assets/21d486fc-387f-4895-a6a9-a78c25ffdda9" />

시스템 실행 및 로그인 후 가장 먼저 표시되는 홈 화면이다. 상단에는 이번 달 남은 가용 예산을 게이지 바(Progress bar) 형태로 시각화하여 지출 한도를 직관적으로 인지할 수 있도록 한다. 하단에는 최근 발생한 거래 내역 리스트를 요약하여 보여주며, 현재 재무 상태를 한눈에 파악할 수 있는 진입점 역할을 한다.

### 4.2 거래 내역 편집 및 추가 (Transaction Edit)
<img width="431" height="576" alt="image" src="https://github.com/user-attachments/assets/b6ac8fe7-1ddc-49e5-a965-7cd7eef4ebc3" />

자동으로 연동된 거래 내역의 정보를 수정하거나, 현금 결제 등의 사유로 새로운 내역을 수동 기입할 때 사용하는 화면이다. 지출/수입 금액, 카테고리(식비, 교통비 등), 날짜, 그리고 상세 메모를 입력 및 수정할 수 있으며, 하단의 저장 버튼을 통해 변경된 데이터를 시스템 DB 및 통계에 즉시 반영한다.

### 4.3 소비 습관 분석 통계 (Analysis Report)
<img width="443" height="585" alt="image" src="https://github.com/user-attachments/assets/3104945d-87dc-4097-9413-66aa74673eb5" />

누적된 거래 데이터를 기반으로 사용자의 소비 패턴을 시각화하여 제공하는 리포트 화면이다. 지출 카테고리별 비중을 파이 차트(Pie chart)로 명확하게 보여주며, 상단에는 "이번 달은 식비 지출이 20% 늘었어요!"와 같은 시스템의 자동 분석 코멘트를 텍스트로 제공하여 지출 습관 개선을 유도한다.

### 4.4 상위 테마 관리 (Theme Group)
<img width="436" height="538" alt="image" src="https://github.com/user-attachments/assets/6b3e5bb5-6f82-44fc-a2be-ee9edfbfa934" />

개별 거래 내역들을 '제주도 여행'과 같은 특정 목적의 상위 테마로 묶어서 관리하는 화면이다. 해당 테마에 할당된 전체 목표 예산 대비 현재까지의 지출 진행률을 상단에 게이지로 표시하며, 하단에는 해당 테마로 그룹화된 세부 거래 내역(항공권, 숙박비, 식비 등)을 모아서 출력한다.

### 4.5 더치페이 계산 (Dutch Pay Calculator)
<img width="446" height="582" alt="image" src="https://github.com/user-attachments/assets/fb794a12-e500-4811-9fcd-3093ea6bd257" />

공동으로 결제한 내역에 대해 분할 금액을 계산하는 더치페이 전용 팝업/화면이다. 1차적으로 결제된 총액이 상단에 표시되고, 참여 인원수를 입력하면 1인당 실제 부담 금액이 자동으로 연산된다. 이후 하단의 '내 가계부에 적용' 버튼을 클릭하면 원본 결제액 대신 계산된 본인 부담금만 지출 통계에 반영된다.

---

## 5. Glossary

| | |
| :--- | :--- |
| **상위 테마 (Theme Group)** | 일반적인 카테고리(식비, 교통비)와 별개로, '제주도 여행'과 같이 특정 목적을 가진 지출들을 묶어 관리하는 단위 |
| **가용 금액 (Available Amount)** | 설정된 총 예산에서 현재까지의 지출과 앞으로 나갈 고정 지출을 제외하고 실제 사용 가능한 남은 금액 |
| **더치페이 계산 (Dutch Pay)** | 한 명이 결제한 총액 중 사용자의 실제 지분만 지출로 반영하기 위해 인원수대로 분할 연산하는 기능 |
| **금융 데이터 연동** | 은행 및 카드사 API를 통해 사용자의 거래 내역을 실시간 또는 주기적으로 자동 수집하는 과정 |
| **임계치 (Threshold)** | 예산 관리 시 사용자에게 경고 알림을 보내기 위한 기준이 되는 지출 비율 (예: 예산의 80%) |
| **대시보드 (Dashboard)** | 여러 가지 통계 데이터나 거래 내역 등 시스템의 핵심 정보를 한 화면에 요약하여 직관적으로 보여주는 메인 사용자 인터페이스 |
| **푸시 알림 (Push Notification)** | 앱이 실행 중이지 않을 때도 시스템 스케줄러가 예산 초과 위험이나 고정 일정 등을 사용자 디바이스 화면에 띄워주는 메시지 기능 |

---

## 6. References

* **가계부 관리 종합 서비스 Conceptualization Document** (이전 단계 산출물 문서)
* **통계청 소비자 물가 동향 및 지출 패턴 통계 자료 (2025-2026)**
* **금융결제원 오픈뱅킹 API 표준 규격서 (v2.0)**
* **객체지향 분석 및 설계 (UML Distilled, Martin Fowler)**
* **모바일 가계부 및 자산관리 앱 UI/UX 트렌드 리포트**
* **개인정보보호법 및 금융 데이터 보안 가이드라인** (오픈뱅킹 API 연동 및 데이터 저장 보안 참고)

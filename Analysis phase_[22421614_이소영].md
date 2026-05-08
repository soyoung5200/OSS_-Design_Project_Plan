#Analysis Document

## 1. Introduction

### 1) Summary
현대인들은 신용카드, 체크카드뿐 아니라 삼성페이, 카카오페이와 같은 간편결제 서비스를 통해 매우 편리하게 소비 활동을 하고 있습니다. 그러나 결제 과정이 지나치게 간단해지면서 사용자는 자신이 얼마를, 어디에, 왜 사용했는지를 체계적으로 파악하기 어려워졌습니다. 그 결과 소득이 있음에도 불구하고 지출 흐름을 명확히 인지하지 못해 경제적 어려움을 겪는 사례가 증가하고 있습니다. 이러한 문제를 해결하기 위해 본 프로젝트에서는 단순한 수기 가계부 기능을 넘어, 금융 데이터 연동, 소비 패턴 분석, 더치페이 계산, 예산 초과 경고, 고정 수입 및 지출 확인 알림 등 다양한 기능을 제공하는 가계부 관리 종합 서비스를 제공합니다. 본 시스템은 사용자의 소비를 자동으로 기록하고 분석하여 과소비를 방지하고, 보다 계획적인 자산 관리를 가능하게 하는 것을 목표로 합니다.
본 문서는 Conceptualization Document에 이어지는 Analysis 단계의 문서로서, 시스템의 기능을 Use case 관점에서 분석하고 상세 시나리오를 정의한다.

### 2) Features of the Service
본 시스템은 사용자의 금융 데이터를 은행 및 카드사와 연동하여 자동으로 수집하고, 수집된 거래 내역을 기반으로 소비를 분석합니다. 또한 사용자가 특정 지출들을 하나의 상위 테마로 묶어 관리할 수 있도록 하며, 더치페이 계산 기능을 통해 실제 개인 부담 금액만 반영할 수 있습니다. 추가적으로 사용자가 월 수입과 고정 지출을 설정하면 가용 금액을 계산하고, 예산 초과 위험 시 경고 알림을 제공합니다. 월급일이나 정기 수입일에는 입금 여부를 확인하도록 알림을 제공하며, 자동 수집된 거래 내역 중 불필요한 항목은 사용자가 직접 수정 또는 삭제할 수 있습니다.

### 3) Goals
이번 Analysis 보고서에서는 제안한 가계부 관리 종합 서비스의 기능들을 체계적으로 정의하기 위해 Use case diagram 및 Use case description을 도출합니다. 각 기능의 성공 경로와 예외 상황을 표준화된 양식으로 기술하여 시스템 설계의 기초를 마련하는 것을 목표로 합니다.

---

## 2. Use case analysis

### 2.1 Use case diagram
본 시스템은 사용자(User)와 외부 금융기관(Bank/Card System) 간의 상호작용을 중심으로 하며, 시스템 내부 스케줄러(Scheduler)가 자동화된 알림 및 분석을 수행합니다.
<img width="659" height="514" alt="UseCaseDiagram" src="https://github.com/user-attachments/assets/50abae6a-fa0f-47f8-bb90-2cc0e102f84e" />

### 2.2 Use case description

#### Use Case #1 : 금융 데이터 연동

**1) GENERAL CHARACTERISTICS**
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
| :--- | :--- |
| **Performance** | API 호출 및 데이터 처리 완료까지 5초 이내 |
| **Frequency** | 앱 접속 시 1회 및 주기적 자동 수행 |
| **Concurrency** | 다중 사용자 동시 접속 시 시스템 지연 방지 처리 필요 |
| **Due Date** | Release 1.0 |

---

#### Use Case #2 : 상위 테마 그룹화

**1) GENERAL CHARACTERISTICS**
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
| :--- | :--- |
| **Performance** | DB 업데이트 및 UI 갱신까지 3초 이내 |
| **Frequency** | 사용자의 필요에 따라 수시 발생 |
| **Concurrency** | 단일 사용자 세션 내에서 처리 |
| **Due Date** | Release 1.0 |

---

#### Use Case #3 : 더치페이 지출 계산

**1) GENERAL CHARACTERISTICS**
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
| :--- | :--- |
| **Performance** | 계산 및 반영까지 2초 이내 |
| **Frequency** | 공동 지출 발생 시마다 수행 |
| **Concurrency** | 병행성 충돌 가능성 낮음 |
| **Due Date** | Release 1.0 |

---

#### Use Case #4 : 가용 금액 경고 알림

**1) GENERAL CHARACTERISTICS**
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
| :--- | :--- |
| **Performance** | 연산 및 알림 발송까지 1초 이내 |
| **Frequency** | 설정 비율 도달 시마다 발생 |
| **Concurrency** | 백그라운드 스케줄러를 통한 개별 처리 |
| **Due Date** | Release 1.5 |

---

#### Use Case #5 : 고정 소득 및 지출 확인 알림

**1) GENERAL CHARACTERISTICS**
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
| :--- | :--- |
| **Performance** | 스케줄링 배치 작업 완료까지 5분 이내 |
| **Frequency** | 설정된 주기(매달 등)마다 발생 |
| **Concurrency** | 대량 알림 발송을 위한 큐 처리 필요 |
| **Due Date** | Release 1.5 |

---

#### Use Case #6 : 소비 습관 분석

**1) GENERAL CHARACTERISTICS**
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
| :--- | :--- |
| **Performance** | 통계 산출 및 시각화까지 5초 이내 |
| **Frequency** | 사용자 요청 시 실시간 또는 정기적 생성 |
| **Concurrency** | 복잡한 쿼리에 대한 DB 최적화 필요 |
| **Due Date** | Release 2.0 |

---

#### Use Case #7 : 연동 내역 편집 및 삭제

**1) GENERAL CHARACTERISTICS**
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
| :--- | :--- |
| **Performance** | 반영 완료까지 3초 이내 |
| **Frequency** | 오류 정정 등을 위해 빈번히 발생 |
| **Concurrency** | 분석 데이터와의 실시간 동기화 필요 |
| **Due Date** | Release 1.0 |

# SOOP Android 앱 개발 공채 과제

# 📊  프로젝트 구조 설명

## 패키지 구조

```
com.example.soop2025
├── SOOP2025Application.kt
├── 📂 data
├── 📂 domain
├── 📂 di
└── 📂 presentation

```

## **Data Layer (데이터 계층)**

- **API 통신**
    - `ReposApiService`, `UserApiService`, `ReposSearchApiService`를 통해 원격 데이터를 처리합니다.
- **응답 핸들링**
    - `ApiResponseHandler`와 `ErrorResponseConverter`를 사용하여 네트워크 응답을 관리합니다.
- **Repository 패턴 적용**
    - `ReposRepository`, `UserRepository`, `ReposSearchRepository` 인터페이스와 이를 구현하는 `DefaultRepository` 클래스들로 구성됩니다.

```
📂 data
├── 📁 local
│   └── 📁 language         // Language 관련 Local DataSource
│       ├── LanguageDataSource.kt
│       └── LocalLanguageDataSource.kt
├── 📁 remote
│   ├── 📁 api              // API 인터페이스 정의
│   │   ├── ReposApiService.kt
│   │   ├── ReposSearchApiService.kt
│   │   └── UserApiService.kt
│   ├── ApiResponseHandler.kt
│   ├── ErrorResponseConverter.kt
│   ├── NetworkFailException.kt
│   └── ResponseResult.kt
├── 📁 model
│   ├── 📁 repossearch      // ReposSearch 관련 응답 모델
│   │   ├── PermissionsResponse.kt
│   │   ├── ReposSearchItemResponse.kt
│   │   ├── ReposSearchResponse.kt
│   │   └── TextMatchResponse.kt
│   ├── 📁 repos            // Repository 관련 응답 모델
│   │   ├── ReposResponse.kt
│   │   └── ReposSummaryResponse.kt
│   ├── 📁 user             // User 관련 응답 모델
│   │   ├── PlanResponse.kt
│   │   ├── UserReposResponse.kt
│   │   └── UserResponse.kt
│   ├── 📁 shared           // 공통 응답 모델
│   │   ├── ErrorResponse.kt
│   │   ├── LicenseResponse.kt
│   │   └── OwnerResponse.kt
│   └── 📁 mapper           // Data -> Domain 변환 매퍼
│       ├── ReposMapper.kt
│       ├── ReposSearchMapper.kt
│       └── UserMapper.kt
└── 📁 repository           // Repository 구현체
    ├── ReposDefaultRepository.kt
    ├── ReposSearchDefaultRepository.kt
    └── UserDefaultRepository.kt

```

## **Domain Layer (도메인 계층)**

- 애플리케이션의 비즈니스 로직과 도메인 모델을 정의합니다.
- 순수 Kotlin 코드로 구성됩니다.

```
domain
├── 📁 model                // Domain 계층 모델
│   ├── 📁 repossearch      // ReposSearch 관련 도메인 모델
│   │   ├── ReposSearch.kt
│   │   ├── ReposSearches.kt
│   │   └── ReposSearchOwner.kt
│   ├── 📁 repos            // Repos 관련 도메인 모델
│   │   └── Repos.kt
│   ├── 📁 user             // User 관련 도메인 모델
│   │   ├── User.kt
│   │   └── UserRepos.kt
│   └── 📁 shared           // 공통 도메인 모델
│       └── Language.kt
└── 📁 repository           // 도메인 계층 Repository 인터페이스
    ├── ReposRepository.kt
    ├── ReposSearchRepository.kt
    └── UserRepository.kt

```

## **Presentation Layer (프레젠테이션 계층)**

- ViewModel(`ReposViewModel`, `ReposSearchViewModel`)을 통해 UI와 데이터를 연결합니다.
- ViewModel은 Hilt로 Data Layer의 Repository 구현체를 주입합니다.

```
📁 presentation
├── 📁 repos                // Repos 관련 UI
│   ├── 📁 component
│   │   ├── RepoInformationText.kt
│   │   ├── UserBottomSheet.kt
│   │   ├── UserModalBottomSheet.kt
│   │   └── UserText.kt
│   ├── ReposDetail.kt
│   ├── ReposScreen.kt
│   ├── ReposUiState.kt
│   ├── UserUiState.kt
│   └── ReposViewModel.kt
├── 📁 search               // Search 관련 UI
│   ├── 📁 component
│   │   ├── SearchItem.kt
│   │   ├── SearchLazyColumn.kt
│   │   └── SearchTopBar.kt
│   ├── ReposSearchScreen.kt
│   ├── ReposSearchUiState.kt
│   └── ReposSearchViewModel.kt
├── 📁 ui                   // 공통 UI 구성 요소
│   ├── 📁 component        // 공통 컴포넌트
│   │   ├── CircularLoading.kt
│   │   └── CoilImage.kt
│   └── 📁 theme            // UI 테마 관련
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
└── 📁 util                 // 공통 유틸리티 클래스
    ├── FormatMetricSuffix.kt
    ├── MainActivity.kt
    └── MainNavHost.kt

```

## **Dependency Injection (의존성 주입)**

- `NetworkModule`, `RetrofitModule`, `RepositoryModule` 등 DI 관련 모듈을 정의했습니다.

```
📂 di
├── DataSourceModule.kt
├── NetworkModule.kt
├── RepositoryModule.kt
└── RetrofitModule.kt
```

---


# 사용 라이브러리

## **🧭 Navigation**

### 📌 **androidx.navigation-compose (2.8.6)**

- **용도**
  - Jetpack Compose 환경에서 화면 간 이동을 간단하게 구현
  - 데이터 전달 및 상태 관리, 상태를 유지하면서 UI를 전환하는 기능 제공.

- **기대 효과**
  - 프래그먼트 없이 **네비게이션을 선언적 방식**으로 구현 가능.
  - **Back Stack 자동 관리**, **Safe Args 없이 매개변수 전달 가능**.
  - `rememberNavController()`를 활용하여 UI 상태를 자연스럽게 유지.

### 📌 **hilt-navigation-compose (1.2.0)**

Jetpack Compose에서 네비게이션과 Hilt ViewModel 주입을 통합해주는 라이브러리

- **도입 상황**
  - 기존에는 activity에서 ViewModel을 주입
  - Screen 이동 시 뷰모델에 이전 데이터가 남아있는 문제 발생
      - `ReposScreen`에서 저장소 상세 정보를 확인한 후 `ReposSearchScreen`에서 새로운 아이템을 선택하면, `ReposScreen`에 첫 번째 아이템 정보가 잠깐 표시된 뒤 두 번째 아이템으로 업데이트됨.
  - `NavGraph`에서 ViewModel을 Screen **단위로 관리**할 수 있도록 개선 필요

- **적용 방식**
  - `ReposSearchScreen`과 `ReposScreen`의 default parameter로 `hiltViewModel()` 호출,
    `MainNavHost`의 화면 전환에 따라 ViewModel을 주입하여 해결

- **기대 효과**
  - 네비게이션 이동 시 ViewModel을 자동으로 관리
      - 같은 `NavBackStackEntry`를 공유하는 컴포저블들이 동일한 ViewModel 인스턴스를 사용
  - ViewModel 인스턴스를 직접 관리할 때의 보일러플레이트 코드 감소 및 간단한 의존성 주입



## 🛜 Networking

### 📌 **okhttp (4.11.0) & okhttp-logging-interceptor**

- **용도**
  - Hilt와 결합해 `OkHttpClient`를 전역으로 관리.
  - logging-interceptor로 API 요청/응답 로깅을 실시간 확인 가능

### 📌  **retrofit (2.11.0) / retrofit-serialization-converter (1.0.0)**
REST API와의 통신을 위한 가장 널리 사용되는 라이브러리로 간결한 코드 작성 지원.

- **용도**
    - 어노테이션 기반으로 REST API 네트워크 통신 관리
    - Kotlinx Serialization으로 JSON 직렬화/역직렬화 지원
    - `suspend fun`으로 코루틴과 결합해 비동기 처리 
    - `retrofit-serialization-converter`를 통해 JSON 데이터를 Kotlin 모델로 변환.

### 📌  **kotlinx-serialization-json (1.6.3)**
Kotlin 공식 직렬화 라이브러리로, 적은 코드로 빠르고 안전한 JSON 변환 제공

- **용도**
    - `@Serializable`로 JSON 데이터를 Kotlin 객체로 변환
    - `@SerialName`를 통해 JSON의 snake case를 Kotlin의 camel case로 변환



## 🗡️ Dependency Injection

### 📌 **hilt-android (2.51.1)**

- **선택 이유**: 기존 Dagger보다 설정이 간단하며, Compose에서도 매끄러운 DI 제공
- **용도**
    - `@Inject` 및 `@HiltViewModel`을 활용하여 **ViewModel, Repository, API Service 관리**
    - `hilt-navigation-compose`를 통해 **NavGraph와 Hilt 연계**
- **기대 효과**
    - **보일러플레이트 코드 감소** (Dagger의 복잡한 Module/Component 설정 불필요)
    - **싱글톤 관리 가능** (`@Singleton`, `@Provides` 사용)



## 🌌 Image Loading

### 📌 **Coil (2.1.0)**

- **선택 이유**
- Kotlin Coroutine 및 Flow를 지원
- Glide, Picasso보다 가볍다

- **용도**
    - url로 네트워크 이미지 로드
    - `AsyncImage`를 사용하여 Compose에서 **간결한 API**로 이미지 표시


## 👩🏻‍💻 Code Style & Format

### 📌 **ktlint (11.6.1) & compose-rules-ktlint (0.4.5)**

- **용도**
  - 코드 스타일을 일관성 있게 유지, 자동 포맷팅 지원
  - Jetpack Compose에서 권장하는 코드 스타일을 유지하기 위해 사용

- **기대 효과**
  - 모든 파일을 개별적으로 확인하지 않아도 한 번에 검증 및 포맷 가능
  - Compose 코드에서 발생할 수 있는 **안티패턴 방지**
  - `.editorconfig` 파일 설정 없이 코틀린 코드 스타일 강제
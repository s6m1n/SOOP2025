# SOOP Android 앱 개발 공채 과제

## 패키지 구조

```
com.example.soop2025
├── 📂 data
├── 📂 domain
├── 📂 di
├── 📂 presentation
└── SOOP2025Application.kt
```


### Data
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


### Dependency Injection
```
📂 di                       // Dependency Injection 관련 모듈
├── DataSourceModule.kt
├── NetworkModule.kt
├── RepositoryModule.kt
└── RetrofitModule.kt
```


### Domain
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


### Presentation
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
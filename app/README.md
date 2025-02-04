# SOOP Android 앱 개발 공채 과제

2025년 SOOP 공채 서류 전형에 합격하신 것을 진심으로 축하드립니다!👏

다음은 1차 면접 전형에 오시기 전 제출해 주셔야 할 과제에 대해서 설명드리겠습니다.

# 📝 과제 소개

- Github API를 사용하여 repository를 검색하는  어플리케이션을 구현해주세요.
- 검색된 Repository를 클릭하면, Repository의 상세정보를 보여주는 상세페이지로 이동하며
  상세페이지의 more 버튼 클릭시 유저 정보를 보여주는 BottomSheet가 표시됩니다.
- 자세한 설명은 다음 과제 내용을 참고해주세요.

  ![full_scenario.gif](https://prod-files-secure.s3.us-west-2.amazonaws.com/eb1f69ef-05cd-4575-b5eb-69ca40c562f4/6f873169-54c9-4b3a-be49-c71275d806d4/full_scenario.gif)


---

# 🛠 과제 내용

## 1️⃣ 요구 사항

### 개발 환경

- Language : Kotlin
- minSdk : 26
- targetSdk : 34

### 메인 페이지

- Repository 검색  API를 활용하여  검색 페이지를  구현해주세요.
- 검색결과의 아이템을 클릭하면 상세페이지로 이동합니다.
- 예시화면

  ![search.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/eb1f69ef-05cd-4575-b5eb-69ca40c562f4/cc6777b7-cdee-4a3a-b586-0e0214fdfece/search.png)

- 필수 표시항목


    | 유저 프로필 이미지 |
    | --- |
    | Repository명 |
    | description |
    | star |
    | 사용언어 |
- 사용API
- Github 검색API(Search repositories)
  (https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-repositories)


    | 필드 | 설명 |
    | --- | --- |
    | owner.avatar_url | 프로필 이미지 |
    | owner.login | 유저명 |
    | name | Repository명 |
    | description | repository 정보 |
    | stargazersCount | star count |
    | language | 언어 |

### 상세 페이지

- Repository 정보 조회 API를 활용하여, Repository의  상세페이지를 구현해주세요.
- more 버튼을 클릭하면 User의 정보가 BottomSheet로 표시됩니다.
- 예시화면

  ![detail.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/eb1f69ef-05cd-4575-b5eb-69ca40c562f4/36ed0115-605b-4d03-803b-4c2e7fef1613/detail.png)

- 필수 표시항목


    | 유저 프로필 이미지 |
    | --- |
    | 유저명 |
    | Repository명 |
    | star |
    | watchers  |
    | Forks |
    | description |

- 사용API
- Repository 조회 API(Get a repository)
  (https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#get-a-repository)


    | 필드 | 설명 |
    | --- | --- |
    | owner.avatar_url | 프로필 이미지 |
    | name | Repository명 |
    | stargazersCount | star count |
    | watchers_count | watchers count |
    | forks_count | forks count |
    | description | repository 정보 |

### BottomSheet

- User의 Repository정보, User정보 API를 활용하여  User정보를 BottomSheet로 구현해주세요.
- 예시화면

  ![bottomsheet.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/eb1f69ef-05cd-4575-b5eb-69ca40c562f4/92343d7c-8575-4c69-b51b-659fcbcfbb70/bottomsheet.png)

- 필수 표시항목


    | 유저 프로필 이미지 |
    | --- |
    | 유저명 |
    | 팔로워 수 |
    | 팔로잉 수 |
    | **유저 Repository들**의 언어 리스트 |
- 사용API
- 유저의 Repository 조회 API(List repositories for a user)
  (https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user)


    | 필드 | 설명 |
    | --- | --- |
    | language | repository에 사용된 언어 |
    
    - 유저 정보 조회 API(Get a user)
    (https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user)
    
    | 필드 | 설명 |
    | --- | --- |
    | avatar_url | 프로필 이미지 |
    | login | 유저명 |
    | followers | 팔로워 수 |
    | following | 팔로잉 수 |
    | forks_count | forks count |

## 2️⃣ 우대 사항

- GitHub README.md에 자신이 구현한 코드를 잘 설명한 경우
- 메모리, 성능 등 최적화를 고려한 경우 (어떤 부분을 생각 하고 고려 했는지 README에 설명)
- UnitTest를 작성하여 코드가 올바르게 동작하는 것을 검증한 경우
- 그 외 내부 기준에 따른 추가 점수 반영 (Coroutine, Compose, Jetpack, Architecture, 멀티모듈 등)

## 3️⃣ 기타 사항

- 라이브러리는 자유롭게 사용 가능합니다.
- 예시 화면은 참고를 위해서 제공된 것이며, 화면 구성은 자유입니다.
- 요구 사항을 전부 구현하지 않아도 부분 점수가 반영되니 기한 내에 제출해주세요.
- 면접 진행 시 코드에 대한 질문이 있을 수 있습니다.

---

# 📮 과제 제출 방법

- 과제는 자신의 GitHub Public Repository에서 작업해주세요.
- 해당 Repository URL을 제출 기한에 맞추어 **joel@sooplive.com**으로 보내주세요.
- 제출 마감 기한 : 2월9일 자정
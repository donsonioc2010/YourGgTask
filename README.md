# YourGGTask

## 개요

> YOUR.GG Test Repository이며 Kotlin을 활용하여 다음과 같은 목표를 이루는것

> Java를 사용가능하다 하였는데, 굳이 Kotlin으로 작성해본 이유는 평소에 Kotlin을 한번 사용해보고 싶었으며,  
> 가장 최근에 진행한 프로젝트가 LOL의 전적검색 프로젝트였고 이미 Java를 활용해서 구현을 완료해봤기 떄문.

- LOL의 전적조회 REST API서버 구축
    - Riot Api를 활용
    - 최근 20경기의 전적 리스트 및 통계 표기
    - 세부지표 자유
    - Test Case

---

## Tech Stack

- Kotlin, JDK11
- Spring Boot 2.7.5

---

## 기능 정리

> 작업해야 할 기능 러프하게 정리

- Summoner
    - [소환사을 기준으로 Summoner 계정정보 조회 및 반환](####소환사을-기준으로-Summoner-계정정보-조회-및-반환)
    - [소환사의 랭크타입 데이터 조회 및 반환](####소환사의-랭크타입-데이터-조회-및-반환)
- Match
    - [최근 20경기의 MatchList 조회 및 반환](####최근-20경기의-MatchList-조회-및-반환)
- Global
    - Bean
        - RestTemplate 설정
        - YML에 지정한 링크, APIKey를 가져올 Configuration
        - Object Mapper 설정
    - Util
        - 소환사명 문자열 치환
- Test Case

---

## 기능별 로직

<details>
<summary> Summoner  </summary>

#### 소환사을 기준으로 Summoner 계정정보 조회 및 반환

1. 소환사 계정 정보 조회 및 반환

#### 소환사의 랭크타입 데이터 조회 및 반환

1. 소환사 계정정보 조회
2. ID값을 통하여 랭크타입 정보 조회 및 반환

</details>

<details>
<summary>Match</summary>

#### 최근 20경기의 MatchList 조회 및 반환

1. 소환사 계정정보 조회
2. 조회한 계정정보의 ID값을 통하여 최근 20경기 조회
    1. 타입지정이 없는 경우 모든게임조회
    2. 있으면 해당 타입만 조회
3. 조회 결과 반환

</details>

---

## Reference List

> Kotlin자체가 처음이 아니다 보니 사용법에 대한 Reference를 계속 찾아보며 진행할 필요가 있었고, 참고한 Reference 포스트 리스트입니다.

### 1. Bean 설정 구현

#### RestTemplate 설정

1. [RestTemplate설정 참고 소스](https://github.com/f-lab-edu/NoobLoL/blob/develop/src/main/java/com/nooblol/global/config/RestTemplateConfig.java)

> 과거 프로젝트 당시에 사용했던 Java소스 Kotlin으로 변환

#### Riot Properties 설정 Reference

1. [log4j2의 설정 포스트](https://www.wool-dev.com/backend-engineering/spring/spring-kotlin-logging-simple)
2. [Properties의 선언 방법](https://velog.io/@lsb156/Spring-Boot-Properties-Usage)
3. [Bean주입](https://jobc.tistory.com/198)

### 2. Summoner 기능 구현

#### 계정 정보 조회 기능 구현

1. [DTO의 선언방법](https://tech.wheejuni.com/2018/03/19/hackathon-kotlin/)
2. [DTO의 ObjectMapper Issue](https://kapentaz.github.io/kotlin/json/Kotlin-and-Jackson-(ObjectMapper)/#)

### 3. Match 기능구현중

1. [Kotlin Enum 활용법](https://banziha104.github.io/2020/05/05/kotlin-enum/)

---

## 제작을 하면서 생긴 궁금증들?

1. `@Slf4j` Annotation을 자주 사용했는데 사용이 불가했었다. 비슷하게 만드는 방법을 몰라서 어쩔 수 없이 직접 선언을 진행했는데, 방법이 존재하는지...?
2. ResponseEntity의 OK를 제외하고 Return이 안된다...

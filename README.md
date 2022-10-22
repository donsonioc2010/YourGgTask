# YourGgTask

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


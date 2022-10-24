# YourGGTask

## Index

1. [개요](##개요)
2. [Tech Stack](##tech-stack)
3. [Git CommitMessage Convention](##git-commitmessage-convention)
4. [기능 정리](##기능-정리)
5. [기능별 로직](##기능별-로직)
6. [필수 설정](##필수-설정)
7. [API](##api)
8. [회고](##회고)
9. [Use Reference List](##use-reference-List)

----

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

## Git CommitMessage Convention

> 커밋 컨벤션은 `태그 : 제목 또는 내용` 형식으로 메세지를 작성하였으며 사용한태그와 사용한 경우는 아래의 표와 같습니다.

태그 | 설명
---- | ----
feat | 기능 구현 개발
refactor | 소스의 수정
test | 테스트케이스의 추가, 수정
docs | 코드의 수정은 없으며, 문서 또는 주석의 수정
chore | Gradle의 수정

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

## 필수 설정

> Tomcat Port의 경우에는 Default Value인 `8080`을 사용.

1. [Riot Developer](https://developer.riotgames.com/)에서 `API Key`를 발급받는다.
2. `src/main/resources/riot.properties`에서 `api-key` 설정에 `1번`항목에서 발급받은 `API Key`를 입력한다.

---

## API

1. `@GET`, `/summoner/info/by-summoner-name?name={summonerName}`

   > 소환사명을 바탕으로 소환사의 계정 정보에 대한 조회

   | RequestType | parameterName | Type | Description | Optional | Default Value |
   --------------| ---------------|----|----|----|---- |---- |
   | RequestParam | name | String | 조회하고자 하는 소환사명|true | |

    ```
    Example :
    http://localhost:8080/summoner/info/by-summoner-name?name=눕는게 일상
    
    {
        "accountId": "-CZ1UdXj_30sT4ZOb1PDJfiCCZvYTRnYRQncqn8LUBE",
        "profileIconId": 585,
        "revisionDate": 1665130481000,
        "name": "눕는게일상",
        "id": "zJM0b_kEhZhHKhq6qsL8f4nusWE-IEegWdeqqK3LA3CrnA",
        "puuid": "KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg",
        "summonerLevel": 230
    }
    ```

2. `@GET`, `/summoner/rank-history/by-summoner-name?name={summonerName}`

   > 요청한 소환사의 랭크별 전적 정보를 조회해 반환한다.

   | RequestType | parameterName |Type | Description | Optional | Default Value |
   --------------| ---------------|----|----|----|----|---- |
   | RequestParam |name | 조회하고자 하는 소환사명|true | | |

   ```
   Example :
   http://localhost:8080/summoner/rank-history/by-summoner-name?name=효총무
   [
      {
          "leagueId": "0e6a9c5c-871c-4f42-bea3-8a58c544506d",
          "summonerId": "v5oAwvnMASyc3y3gGZjBD-sYAzrK9_0Qp97P50s0KbdIgWk",
          "summonerName": "효총무",
          "queueType": "RANKED_FLEX_SR",
          "tier": "SILVER",
          "rank": "II",
          "leaguePoints": 43,
          "wins": 37,
          "losses": 43,
          "hotstreak": false,
          "veteran": false,
          "freshBlood": false,
          "inactive": false,
          "miniSeries": null
      },
      {
          "leagueId": "6a0239e3-dddd-4dba-b86a-734600f230b8",
          "summonerId": "v5oAwvnMASyc3y3gGZjBD-sYAzrK9_0Qp97P50s0KbdIgWk",
          "summonerName": "효총무",
          "queueType": "RANKED_SOLO_5x5",
          "tier": "BRONZE",
          "rank": "I",
          "leaguePoints": 73,
          "wins": 4,
          "losses": 7,
          "hotstreak": false,
          "veteran": false,
          "freshBlood": false,
          "inactive": false,
          "miniSeries": null
      }
   ]

    ```
3. `@GET`,`/match/ids`
   > 조회를 요청한 Puuid의 최근 진행한 게임에 대한 MatchId List를 획득한다. .

   | RequestType | ParameterName | Type | Description | Optional | Default Value |  
   --------------| ---------------|----|---------------------|---------------|----|----|  
   | RequestParam |puuid | String| 조회하고자 하는 소환사명 |true | |
   | RequestParam |matchType | String | 조회하고자 하는 게임 타입 | false| ALL|
   | RequestParam |start| Int | 조회를 시작해오고자 하는 Index Number| false | 0|
   | RequestParam |count|Int| 조회해오고자 하는 건수| false | 0|

     ```
    Example : 
    1. http://localhost:8080/match/ids?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg
    2. http://localhost:8080/match/ids?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg&matchType=RANK
    3. http://localhost:8080/match/ids?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg&matchType=RANK&start=30
    4. http://localhost:8080/match/ids?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg&matchType=RANK&start=30&count=100
    
    
    [
         "KR_6160684900",
         "KR_6160661735",
         "KR_6160690509",
         "KR_6160508793",
         "KR_6130457885",
         "KR_6130401099",
         "KR_6130316540",
         "KR_6125715102",
         "KR_6125602712",
         "KR_6125569665",
         "KR_6118748223",
         "KR_6118802018",
         "KR_6118692418",
         "KR_6114542182",
         "KR_6114329157",
         "KR_6114356878",
         "KR_6114354178",
         "KR_6114282423",
         "KR_6114280376",
         "KR_6113332670"
     ]
    ```


4. `@GET`, `/match/list`
   > 최근 경기 전적 조회 데이터를 반환한다.
   > ※ Rate Limit로 인하여 Count에 많은 숫자를 넣을 경우 개발자 ApiKey로는 검색에 제한이 발생한다

   | RequestType | ParameterName | Type | Description | Optional | Default Value |
   |--------------| ---------------|----|---------------------|---------------|----|----|
   | RequestParam |puuid | String| 조회하고자 하는 소환사명 |true | |
   | RequestParam |matchType | String | 조회하고자 하는 게임 타입 | false| ALL|
   | RequestParam |start| Int | 조회를 시작해오고자 하는 Index Number| false | 0|
   | RequestParam |count|Int| 조회해오고자 하는 건수| false | 0|

    ```
   Example:
   1. http://localhost:8080/match/list?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg
   2. http://localhost:8080/match/list?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg&matchType=RANK
   3. http://localhost:8080/match/list?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg&matchType=RANK&start=30 
   4. http://localhost:8080/match/list?puuid=KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg&matchType=RANK&start=30&count=20
   [
    {
        "metadata": {
            "dataVersion": "2",
            "matchId": "KR_6160684900",
            "participants": [
                "YWpAEJHmHaJvHeG4bvew8nXo5OtbbIKE3dErg7A_sFSRZLRGJfeJ63MEcPnMB_v_sZJAvYHYIoUFvw",
                "CxXfBzUbGysIgPIA1170-X7rlFqe-lbfxO2ZTob62Q5qctlIVpDJQSKUItcZHY-Vo03oUsRrimCJPw",
                "739ltQM4TmV5KIN9RHVO-kS8tzWG6cz0H3d5KKmfePYHNs2RFOi_G40ZMhcd_I6cb51j6IR3N5GbtQ",
                "0fh82kOHaSmW9bQ4BQJhzMYkP4eZVfinF8GvMXbv1FAJUpOdlYboDX4-TlggCJGCA5q6fPoTDkjn6w",
                "a5GzH80EKYi5i2d3xynNUtz_EVbwoIkRThDz5hGAD3E0jGrKwM8zhQXbFhv_Ath8ekk3MePoGzGvQg",
                "RT1nGvz-0P2ZgDNPprsAN3yM0l_zbl4BgoXjGteO7EhKoE0KWJZcQiIevU27MEUuxuabcJUEBRdKoQ",
                "nOLH17awaF4b6MOO-dPtZzJJtAGUaE_woEC7MfLzGDwxvTSfDcg_Xx1AFsTSicDd5P1tQN7-SBXPRw",
                "KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg",
                "y3Q68ekIHvQYEfErvNrtdd9GWCWIHGi_0TcDNivbh0fLDT2hAouVqr-zPxTgbN848HRZc0K-651stA",
                "dIzhAnrJZ5JDKApjJ0WCcLnEyWfPgRJodIoLEFuUaOXED6QcgBBwi8Q-Ef-pwPX5dh2ZNn5jX45-sg"
            ]
        },
        "info": {
            "gameCreation": 1665126653636,
            "gameDuration": 2060,
            "gameEndTimestamp": 1665128733844,
            "gameId": 6160684900,
            "gameMode": "CLASSIC",
            "gameName": "teambuilder-match-6160684900",
            "gameStartTimestamp": 1665126672788,
            "gameType": "MATCHED_GAME",
            "gameVersion": "12.19.471.6581",
            "mapId": 11,
            ~~~
    }
   ]
    ```

---

## 회고

다른 작업을 한다고.. 2일을 늦게 시작한게 시간이 부족한 결과를 초래해서 아쉬움이 많은 작업이었다.  
시간이 부족해서 아쉬웠던 작업은 다음의 항목들이다.. 그냥 바로 시작했으면 아쉬움이 좀 덜했을듯하다

1.정말 단순하게 경기들에 대한 전적 데이터의 조회기능밖에 제작하지 못한 것  
2.Mock을 활용한 테스트 케이스를 제작하지 못한것  
3.Response Header에서 RateLimit 값을 가져와서 요청량을 초과하면 더이상 기능실행을 하도록 하는 모듈의 제작을 못한점


---

## Use Reference List

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

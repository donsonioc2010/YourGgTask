package com.example.yourggtask.match.service

import com.example.yourggtask.global.utils.RiotConstants
import com.example.yourggtask.global.utils.enum.MatchRankType
import org.apache.commons.lang3.StringUtils
import org.apache.http.NameValuePair
import org.apache.http.client.utils.URIBuilder
import org.apache.http.message.BasicNameValuePair
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.Optional

@Service
class MatchService(
    private val restTemplate: RestTemplate,
    private val riotConstants: RiotConstants,
    private val httpHeaders: HttpHeaders
) {
    private val log = LoggerFactory.getLogger(MatchService::class.java)

    /**
     * `startNum`번째 부터 `countNum`개의 갯수만큼 `matchRankType`과 일치하는 `puuid`사용자의 최근 경기ID List를 반환한다.
     * @param puuid         조회를 희망하는 소환사의 Puuid
     * @param matchRankType 조회를 희망하는 RankType, 만약 Parameter가 존재하지 않는 경우에는 모든 타입의 MatchId를 조회한다
     * @param startNum      조회를 희망하는 순번째 Default == 0
     * @param countNum      조회를 희망하는 건수 Default == 20
     */
    fun getMatchIdListByPuuid(
        puuid: String,
        matchRankType: MatchRankType,
        startNum: Int? = 0,
        countNum: Int? = 20
    ): ResponseEntity<ArrayList<String>> {

        if (StringUtils.isBlank(puuid)) {
            log.warn("getMatchIdListByPuuid BadRequest Error")
            return ResponseEntity.badRequest().build()
        }

        var param: List<NameValuePair> = emptyList()

        param += BasicNameValuePair("start", startNum.toString())
        param += BasicNameValuePair("count", countNum.toString())

        if (Optional.ofNullable(matchRankType)
                .orElse(MatchRankType.ALL) != MatchRankType.ALL
        )
            param += BasicNameValuePair("type", matchRankType.typeValue)

        var searchUrl =
            URIBuilder(riotConstants.matchListByPuuid.replace("{puuid}", puuid))
                .addParameters(param).build()

        log.info("getMatchIdListByPuuid : Request Puuid : $puuid, MatchRankType : $matchRankType")
        log.info("getMatchIdListByPuuid : Request URL : ${searchUrl.toString()}")


        return restTemplate.exchange(
            searchUrl, HttpMethod.GET, HttpEntity<String>(httpHeaders),
            object : ParameterizedTypeReference<ArrayList<String>>() {}
        )
    }
}
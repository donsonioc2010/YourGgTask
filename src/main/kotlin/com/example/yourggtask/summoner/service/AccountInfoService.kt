package com.example.yourggtask.summoner.service

import com.example.yourggtask.global.utils.RiotConstants
import com.example.yourggtask.global.utils.StringUtils.Companion.summonerNameWhiteSpaceReplace
import com.example.yourggtask.summoner.dto.LeagueEntryDto
import com.example.yourggtask.summoner.dto.SummonerDTO
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.IllegalArgumentException
import org.springframework.core.ParameterizedTypeReference as ParameterizedTypeReference


@Service
class AccountInfoService(
    private val restTemplate: RestTemplate,
    private val riotConstants: RiotConstants,
    private val httpHeaders: HttpHeaders
) {
    private val log = LoggerFactory.getLogger(AccountInfoService::class.java)

    /**
     * @param summonerName 계정 정보를 조회하고자 하는 소환사명
     */
    fun getAccountInfoBySummonerName(summonerName: String): ResponseEntity<SummonerDTO> {
        if (StringUtils.isBlank(summonerName)) {
            log.warn(this.javaClass.methods.toString() + "summoner name isBlank : $summonerName")
            throw IllegalArgumentException("Summoner Name is Blank")
        }

        var searchUrl: String =
            riotConstants.summonerInfoBySummonerName.replace(
                "{summonerName}",
                summonerNameWhiteSpaceReplace(summonerName.trim())
            )
        log.info("[SummonerController.getAccountInfo] RequestUrl : ${searchUrl}")

        return restTemplate.exchange(
            searchUrl, HttpMethod.GET, HttpEntity<String>(httpHeaders), SummonerDTO::class.java
        )
    }

    /**
     * getAccountInfoId의 ID값을 가져와야 한다.
     */
    fun getAccountRankTypeDataListBySummonerName(summonerName: String): ResponseEntity<List<LeagueEntryDto>> {
        var summonerId = getSummonerIdBySummonerName(summonerName)

        var searchUrl = riotConstants.leagueSummonerRankDataBySummonerId.replace(
            "{encryptedSummonerId}",
            summonerId
        )
        log.info("[SummonerController.getAccountRankTypeDataListBySummonerName] RequestUrl : ${searchUrl}")
        return restTemplate.exchange(
            searchUrl,
            HttpMethod.GET,
            HttpEntity<String>(httpHeaders),
            object : ParameterizedTypeReference<List<LeagueEntryDto>>() {}
        )
    }

    fun getSummonerIdBySummonerName(summonerName: String): String {
        var searchData: SummonerDTO? = getAccountInfoBySummonerName(summonerName).body
        if (searchData != null) {
            return searchData.id
        }
        log.warn("[SummonerController.getSummonerDtoIdBySummonerName] Request Summoner Id Is Null : ${summonerName}")
        throw IllegalArgumentException("Not Found Data")
    }
}
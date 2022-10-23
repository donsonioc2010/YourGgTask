package com.example.yourggtask.summoner.service

import com.example.yourggtask.global.utils.RiotConstants
import com.example.yourggtask.global.utils.StringUtils.Companion.summonerNameWhiteSpaceReplace
import com.example.yourggtask.summoner.dto.SummonerDTO
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import org.springframework.web.client.RestTemplate
import java.lang.IllegalArgumentException

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
                "{summonerName}", summonerNameWhiteSpaceReplace(summonerName.trim())
            )


        return restTemplate.exchange(
            searchUrl, HttpMethod.GET, HttpEntity<String>(httpHeaders), SummonerDTO::class.java
        )
    }
}
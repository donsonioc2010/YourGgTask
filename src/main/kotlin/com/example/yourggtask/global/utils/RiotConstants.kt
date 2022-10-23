package com.example.yourggtask.global.utils


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.util.*

/**
 * Read Riot Properties
 */
@Component
@PropertySource("classpath:riot.properties")
class RiotConstants {


    @Value("\${api-key}")
    lateinit var apiKey: String

    @Value("\${summoner.by-name}")
    lateinit var summonerInfoBySummonerName: String

    @Value("\${match.match-id-list.by-puuid}")
    lateinit var matchListByPuuid: String

    @Value("\${match.match-detail-info.by-match-id}")
    lateinit var matchInfoByMatchId: String

    @Bean
    fun httpHeader(): HttpHeaders {
        var httpHeaders: HttpHeaders = HttpHeaders()
        httpHeaders.add("X-Riot-Token", apiKey)
        return httpHeaders
    }

    override fun toString(): String {
        return "\nRiotConstants(apiKey='$apiKey'\nsummonerInfoBySummonerName=${summonerInfoBySummonerName}\nmatchListByPuuid='$matchListByPuuid'\n matchInfoByMatchId='$matchInfoByMatchId')"
    }
}
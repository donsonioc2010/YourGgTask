package com.example.yourggtask.global.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

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

    override fun toString(): String {
        return "\nRiotConstants(apiKey='$apiKey'\nsummonerInfoBySummonerName=${summonerInfoBySummonerName}\nmatchListByPuuid='$matchListByPuuid'\n matchInfoByMatchId='$matchInfoByMatchId')"
    }
}
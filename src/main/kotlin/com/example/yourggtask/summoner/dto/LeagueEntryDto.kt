package com.example.yourggtask.summoner.dto

/**
 * 랭크정보
 */
data class LeagueEntryDto(
    val leagueId: String,
    val summonerId: String,
    val summonerName: String,
    val queueType: String,
    val tier: String,
    val rank: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val hotstreak: Boolean,
    val veteran: Boolean,
    val freshBlood: Boolean,
    val inactive: Boolean,
    val miniSeries: MiniSeriesDto? = null
) {

}

package com.example.yourggtask.summoner.dto

data class SummonerDto(
    val accountId: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val name: String,
    val id: String,
    val puuid: String,
    val summonerLevel: Long
) {
}
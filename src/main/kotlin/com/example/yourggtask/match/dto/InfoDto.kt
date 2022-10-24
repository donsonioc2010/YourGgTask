package com.example.yourggtask.match.dto

data class InfoDto(
    val gameCreation: Long?,
    val gameDuration: Long?,
    val gameEndTimestamp: Long?,
    val gameId: Long?,
    val gameMode: String?,
    val gameName: String?,
    val gameStartTimestamp: Long?,
    val gameType: String?,
    val gameVersion: String?,
    val mapId: Int?,
    val participants: List<ParticipantDto>?,
    val platformId: String?,
    val queueId: Int?,
    val teams: List<TeamDto>?,
    val tournamentCode: String?
)

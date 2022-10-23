package com.example.yourggtask.summoner.dto

/**
 * 승급전 진행하고 있을 경우 DTO
 */
data class MiniSeriesDto(
    val losses: Int,
    val progress: String,
    val target: Int,
    val wins: Int
) {

}

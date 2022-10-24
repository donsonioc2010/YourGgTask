package com.example.yourggtask.match.dto

import com.example.yourggtask.match.dto.objective.ObjectivesDto

data class TeamDto(
    val bans: List<BanDto>?,
    val objectives: ObjectivesDto?,
    val teamId: Int?,
    val win: Boolean?
) {

}

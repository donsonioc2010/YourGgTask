package com.example.yourggtask.match.controller

import com.example.yourggtask.global.utils.enum.MatchRankType
import com.example.yourggtask.match.service.MatchService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

@RequestMapping("/match")
@RestController
class MatchController(private val matchService: MatchService) {
    private val log = LoggerFactory.getLogger(MatchController::class.java)

    /**
     * 해당 사용자의 최근 경기 MatchId조회
     * @param name : 조회하고자 하는 소환사의 puuid
     * @param matchType : 솔랭, 튜토리얼, 전체 등등 조회 타입
     * @param start : 조회를 할 시작 Index
     * @param count: 데이터 조회 건수
     */
    @GetMapping("/ids")
    fun getMatchIdListByPuuid(
        @NotBlank @RequestParam(value = "puuid") puuid: String,
        @RequestParam(value = "matchType", defaultValue = "ALL") matchType: String,
        @RequestParam(value = "start", defaultValue = "0") startNum: Int,
        @RequestParam(value = "count", defaultValue = "20") count: Int,
    ): ResponseEntity<ArrayList<String>> {
        log.info("[MatchController.getMatchIdListByPuuid] Request Puuid : $puuid")

        return matchService.getMatchIdListByPuuid(
            puuid, MatchRankType.findEnum(matchType).get(), startNum, count
        )
    }


}
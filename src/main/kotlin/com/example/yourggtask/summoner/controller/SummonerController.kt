package com.example.yourggtask.summoner.controller

import com.example.yourggtask.summoner.dto.SummonerDTO
import com.example.yourggtask.summoner.service.AccountInfoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import javax.validation.constraints.NotBlank

/**
 * SummonerName을 받아 계정정보와 랭크데이터를 반환
 */
@RequestMapping("/summoner")
@RestController
class SummonerController(private val accountInfoService: AccountInfoService) {
    private val log = LoggerFactory.getLogger(SummonerController::class.java)


    /**
     * 소환사명(닉네임)을 받아서 Riot API로 확인하여 존재유무와 계정 정보에 대해서 Return한다
     * @param SummonerName 소환사명
     */
    @GetMapping("/info/by-summoner-name")
    fun getAccountInfo(@NotBlank @RequestParam(value = "name") summonerName: String): ResponseEntity<SummonerDTO> {
        log.info("[SummonerController.getAccountInfo] Search SummonerName : $summonerName")
        return accountInfoService.getAccountInfoBySummonerName(summonerName)
    }

    /**
     * RestTemplate 진행시 오류가 발생할 경우 Handling
     * [TODO] : NotFound의 경우 Body가 없는 부분은 추후 수정
     */
    @ExceptionHandler(HttpClientErrorException.NotFound::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleNotFound(e: HttpClientErrorException) {
        log.warn("Summoner Controller Not Found Exception Handle", e)
    }
}


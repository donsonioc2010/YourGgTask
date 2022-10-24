package com.example.yourggtask.summoner.service


import com.example.yourggtask.global.utils.RiotConstants
import com.example.yourggtask.summoner.dto.SummonerDto
import org.junit.jupiter.api.Assertions.*;
import org.mockito.BDDMockito.*;
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class AccountInfoServiceTest {

    @Mock
    lateinit var restTemplate: RestTemplate

    @Mock
    lateinit var riotConstants: RiotConstants

    @Mock
    lateinit var httpHeaders: HttpHeaders

    @Mock
    lateinit var accountInfoService: AccountInfoService

    @Nested
    @DisplayName("소환사의 정보 검색기능 테스트")
    inner class SearchSummonerInfo {
        @Test
        @DisplayName("소환사명의 계정정보 조회시, 공백인 경우 Exception이 발생한다")
        fun getAccountInfoBySummonerName_WhenSummonerNameIsBlank_ThenThrowsException() {
            //given
            var summonerName: String = " "

            //when & then
            assertThrows(IllegalArgumentException::class.java) {
                accountInfoService.getAccountInfoBySummonerName(summonerName)
            }
        }

        @Test
        @DisplayName("소환사명의 계정정보 조회시, Riot에 해당 소환사가 없는 경우 NotFound Exception이 발생한다 ")
        fun getAccountInfoBySummonerName_WhenSummonerNameIsNotFound_ThenNotFoundException() {
            //given
            var summonerName: String = "이것은 없는 소환사명이여"

            //mock
            `when`(
                riotConstants.summonerInfoBySummonerName
            ).thenReturn("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}")

            `when`(
                restTemplate.exchange(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.any(HttpMethod::class.java),
                    ArgumentMatchers.any(),
                    ArgumentMatchers.any<Class<SummonerDto>>()
                )
            ).thenThrow(HttpClientErrorException(HttpStatus.NOT_FOUND))

            //when & then
            assertThrows(HttpClientErrorException.NotFound::class.java) {
                accountInfoService.getAccountInfoBySummonerName(summonerName)
            }
        }

        @Test
        @DisplayName("소환사명의 계정정보 조회시, Riot에 해당 소환사가 존재하는 경우 계정정보를 획득한다")
        fun getAccountInfoBySummonerName_WhenSummonerNameIsExists_ThenReturnSummonerDto() {
            //given
            var summonerName: String = "존재하는소환사"

            var returnData: SummonerDto = SummonerDto(
                "-CZ1UdXj_30sT4ZOb1PDJfiCCZvYTRnYRQncqn8LUBE",
                585,
                1665130481000,
                "눕는게일상",
                "zJM0b_kEhZhHKhq6qsL8f4nusWE-IEegWdeqqK3LA3CrnA",
                "KCSH-FOif2FOuoIFTkXclVK__08YQq8d4H7t96SNpLOVWUU8VDFA_2byLFMGlV_L3jZ0p_cRj-TYUg",
                230
            )

            var mockReturn = ResponseEntity.ok().body(returnData)

            //mock
            `when`(
                riotConstants.summonerInfoBySummonerName
            ).thenReturn("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}")

            `when`(
                restTemplate.exchange(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.any(HttpMethod::class.java),
                    ArgumentMatchers.any(),
                    ArgumentMatchers.any<Class<SummonerDto>>()
                )
            ).thenThrow(HttpClientErrorException(HttpStatus.NOT_FOUND))

            //when & then
            assertThrows(HttpClientErrorException.NotFound::class.java) {
                accountInfoService.getAccountInfoBySummonerName(summonerName)
            }
        }
    }


}
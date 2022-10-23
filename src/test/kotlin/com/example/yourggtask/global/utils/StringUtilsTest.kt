package com.example.yourggtask.global.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory


internal class StringUtilsTest {
    val logger: Logger = LoggerFactory.getLogger(StringUtilsTest::class.java)

    @Test
    fun `소환사명 공백이 존재하는 경우, 공백을 제거한 소환사명을 획득한다`() {
        var orgSummonerName: String = "눕는게 일상 일상"

        var newSummonerName = StringUtils.summonerNameWhiteSpaceReplace(orgSummonerName)

        logger.info("[StringUtilsTest : 공백 테스트 , 정상] : OrgSummoner : $orgSummonerName, NewSummoner: $newSummonerName")

        assertEquals(newSummonerName, "눕는게%20일상%20일상")
    }

    @Test
    fun `소환사명이 공백만 있는 경우, Exception이 발생한다`() {
        var summonerName: String = ""
        logger.info("[StringUtilsTest : 공백 Exception테스트] Test Summoner: ${summonerName}")
        assertThrows(IllegalArgumentException::class.java) {
            StringUtils.summonerNameWhiteSpaceReplace(summonerName)
        }
    }

}
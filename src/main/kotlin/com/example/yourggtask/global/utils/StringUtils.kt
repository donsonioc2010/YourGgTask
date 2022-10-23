package com.example.yourggtask.global.utils

import org.apache.commons.lang3.StringUtils
import org.springframework.util.ObjectUtils


class StringUtils {
    companion object {
        //Optional로 간단하게 해결이 가능할 것 같은데 생각이 안난다..
        fun summonerNameWhiteSpaceReplace(summonerName: String): String {
            var trimSummonerName: String = summonerName.trim()
            if (StringUtils.isBlank(trimSummonerName))
                throw IllegalArgumentException("[StringUtils] : SummonerName Is Blank")

            return trimSummonerName.replace(" ", "")
        }
    }
}

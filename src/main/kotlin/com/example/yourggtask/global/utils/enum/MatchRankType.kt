package com.example.yourggtask.global.utils.enum

import java.util.*
import java.util.function.Supplier

enum class MatchRankType(val typeValue: String) {
    ALL("all"),
    RANK("ranked"),
    NORMAL("normal"),
    TUTORIAL("tutorial"),
    TOURNEY("tourney");

    companion object {
        fun findEnum(findEnumType: String): Optional<MatchRankType> {
            return Arrays.stream(MatchRankType.values()).filter { rankType ->
                rankType.name.uppercase(Locale.getDefault()) == findEnumType.uppercase(Locale.getDefault())
            }.findFirst()
        }
    }
}
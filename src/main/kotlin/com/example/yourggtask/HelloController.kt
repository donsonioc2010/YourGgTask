package com.example.yourggtask

import com.example.yourggtask.global.utils.RiotConstants
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Test
 */
@Slf4j
@RestController
class HelloController(
    private val riotConstants: RiotConstants
) {
    val logger: Logger = LoggerFactory.getLogger(HelloController::class.java)

    @GetMapping("/")
    fun hello(): String {
        logger.info(riotConstants.toString())

        return "hello"
    }
}
package com.example.yourggtask.global.config

import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    // Setting Connection Pool
    @Bean
    fun httpClient(): HttpClient {
        return HttpClientBuilder.create()
            .setMaxConnTotal(10)
            .setMaxConnPerRoute(10)
            .build()
    }

    // Set Timeout And HttpClient
    @Bean
    fun requestFactory(httpClient: HttpClient): HttpComponentsClientHttpRequestFactory {
        var requestFactory: HttpComponentsClientHttpRequestFactory =
            HttpComponentsClientHttpRequestFactory()

        requestFactory.setReadTimeout(5000)
        requestFactory.setConnectTimeout(3000)
        requestFactory.httpClient = httpClient

        return requestFactory
    }

    //Set RequestFactory In RestTemplate
    @Bean
    fun RestTemplate(requestFactory: HttpComponentsClientHttpRequestFactory): RestTemplate {
        return RestTemplate(requestFactory)
    }
}
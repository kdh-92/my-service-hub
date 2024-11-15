package com.kdh.auth

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI

@Component
class JwtAuthentication(
    private val jwtTokenProvider: JwtTokenProvider
) : AbstractGatewayFilterFactory<JwtAuthentication.Config>(Config::class.java) {

    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain ->
            val authHeader = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)

            if (authHeader.isNullOrBlank() || !authHeader.startsWith("Bearer ") || !jwtTokenProvider.isTokenValid(authHeader.substring(7))) {
                return@GatewayFilter redirectToLogin(exchange)
            }

            return@GatewayFilter chain.filter(exchange)
        }
    }

    private fun redirectToLogin(exchange: ServerWebExchange): Mono<Void> {
        val response = exchange.response
        response.statusCode = HttpStatus.FOUND
        response.headers.location = URI.create("/login")
        return response.setComplete()
    }
}
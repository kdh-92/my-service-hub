package com.kdh.auth

import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.net.URI

@Component
class OAuth2SuccessHandler(
    private val jwtTokenProvider: JwtTokenProvider
): ServerAuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        exchange: WebFilterExchange,
        authentication: Authentication
    ): Mono<Void> {
        val oauth2User = authentication.principal as OAuth2User
        val email = oauth2User.attributes["email"] as String
        val name = oauth2User.attributes["name"] as String
        val picture = oauth2User.attributes["picture"] as String

        // todo 회원가입 처리
        // userService.processOAuth2User(email, name, picture)

        val jwt = jwtTokenProvider.generateToken(email, name, picture)
        val response = exchange.exchange.response
        response.headers.location = URI.create("/login/success?token=$jwt")
        response.statusCode = HttpStatus.FOUND

        return response.setComplete()
    }
}

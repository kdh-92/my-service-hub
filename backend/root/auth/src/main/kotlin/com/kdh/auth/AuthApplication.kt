package com.kdh.auth

import io.jsonwebtoken.Jwts
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.security.Principal

@SpringBootApplication
class AuthApplication
fun main(args: Array<String>) {
    SpringApplication.run(AuthApplication::class.java, *args)
}

@RestController
class UserController(
    private val encoder: PasswordEncoder,
    private val users: ReactiveUserDetailsService,
) {

    @PostMapping("/login")
    suspend fun login(@RequestBody login: Login): Jwt {
        val user = users.findByUsername(login.username).awaitSingleOrNull()

        user?.let {
            if (encoder.matches(login.password, user.password)) {
                return Jwt("success")
            }
        }

        throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
    }

    @GetMapping("/test")
    suspend fun test(@AuthenticationPrincipal principal: Principal): Profile {
        return Profile(principal.name)
    }
}

data class Login(val username: String, val password: String)
data class Jwt(val token: String)
data class Profile(val username: String)
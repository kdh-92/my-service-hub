package com.kdh.auth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@SpringBootApplication
class AuthApplication
fun main(args: Array<String>) {
    SpringApplication.run(AuthApplication::class.java, *args)
}

@RestController
class UserController {

    @GetMapping("/test")
    suspend fun test(@AuthenticationPrincipal principal: Principal): Profile {
        return Profile(principal.name)
    }
}

data class Profile(val username: String)
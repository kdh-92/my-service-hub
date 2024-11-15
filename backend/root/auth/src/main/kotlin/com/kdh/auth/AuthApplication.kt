package com.kdh.auth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class AuthApplication
fun main(args: Array<String>) {
    SpringApplication.run(AuthApplication::class.java, *args)
}

@RestController
class AuthController {

    @GetMapping("/login/success")
    fun success(@RequestParam("token") token: String?): ResponseEntity<String> {
        if (token.isNullOrBlank()) {
            return ResponseEntity.status(HttpStatus.OK).body("Authorization header is missing.")
        }

        return ResponseEntity.ok(token)
    }
}

package com.kdh.market

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class MarketApplication
fun main(args: Array<String>) {
    SpringApplication.run(MarketApplication::class.java, *args)
}

@RestController
class MarketsController {

    @GetMapping("/api/v1/market")
    fun market(): ResponseEntity<String> {
        return ResponseEntity.ok("Market Get OK")
    }
}

package com.kdh.auth

import exception.CustomException
import exception.ErrorCode
import exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import reactor.core.publisher.Mono

@RestControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): Mono<ResponseEntity<ErrorResponse>> {
        val errorResponse = ErrorResponse.from(e.errorCode)
        return Mono.just(ResponseEntity.status(errorResponse.status).body(errorResponse))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): Mono<ResponseEntity<ErrorResponse>> {
        val errorResponse = ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR)
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse))
    }
}

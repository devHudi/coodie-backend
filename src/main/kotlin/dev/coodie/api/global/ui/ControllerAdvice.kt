package dev.coodie.api.global.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<ErrorDto> {
        val message = e.message ?: "잘못된 입력입니다."
        return ResponseEntity.badRequest().body(ErrorDto(message))
    }
}

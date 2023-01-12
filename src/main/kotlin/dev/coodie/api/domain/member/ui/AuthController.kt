package dev.coodie.api.domain.member.ui

import dev.coodie.api.domain.member.application.AuthService
import dev.coodie.api.domain.member.dto.MemberJoinRequest
import dev.coodie.api.domain.member.dto.MemberJoinResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/auth")
class AuthController(
    val authService: AuthService
) {

    @PostMapping
    fun join(@RequestBody request: MemberJoinRequest): ResponseEntity<MemberJoinResponse> {
        val response = authService.join(request)
        return ResponseEntity.created(URI.create("/api/members/${response.id}")).body(response)
    }
}

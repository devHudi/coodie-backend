package dev.coodie.api.domain.member.ui

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import dev.coodie.api.domain.member.application.AuthService
import dev.coodie.api.fixture.createMemberJoinRequest
import dev.coodie.api.fixture.createMemberJoinResponse
import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AuthController::class)
class AuthControllerTest : StringSpec() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockkBean
    lateinit var authService: AuthService

    init {
        "회원가입" {
            every { authService.join(any()) } returns createMemberJoinResponse()

            val request = MockMvcRequestBuilders.post("/api/auth")
                .content(objectMapper.writeValueAsString(createMemberJoinRequest()))
                .contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(request)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("id", 1L).exists())
        }
    }
}

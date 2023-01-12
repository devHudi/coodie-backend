package dev.coodie.api.domain.post.ui

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import dev.coodie.api.domain.post.application.PostService
import dev.coodie.api.fixture.createPostCreateRequest
import dev.coodie.api.fixture.createPostCreateResponse
import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(PostController::class)
class PostControllerTest : StringSpec() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockkBean
    lateinit var postService: PostService

    init {
        "포스트 생성" {
            every { postService.createPost(any()) } returns createPostCreateResponse()

            val request = MockMvcRequestBuilders.post("/api/posts")
                .content(objectMapper.writeValueAsString(createPostCreateRequest()))
                .contentType(MediaType.APPLICATION_JSON)

            mockMvc.perform(request)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("id", 1L).exists())
        }
    }
}

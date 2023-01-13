package dev.coodie.api.domain.post.ui

import dev.coodie.api.domain.post.application.PostService
import dev.coodie.api.domain.post.dto.PostCreateRequest
import dev.coodie.api.domain.post.dto.PostCreateResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun createPost(@RequestBody request: PostCreateRequest): ResponseEntity<PostCreateResponse> {
        val response = postService.createPost(request)
        return ResponseEntity.created(URI.create("/api/posts/${response.id}")).body(response)
    }
}

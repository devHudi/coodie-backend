package dev.coodie.api.domain.post.ui

import dev.coodie.api.domain.post.application.PostService
import dev.coodie.api.domain.post.dto.PostCreateRequest
import dev.coodie.api.domain.post.dto.PostCreateResponse
import dev.coodie.api.domain.post.dto.PostResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/{authorUsername}/{slug}")
    fun getPost(
        @PathVariable authorUsername: String,
        @PathVariable slug: String
    ): ResponseEntity<PostResponse> {
        val response = postService.getPost(authorUsername, slug)
        return ResponseEntity.ok(response)
    }
}

package dev.coodie.api.domain.post.application

import dev.coodie.api.domain.member.domain.MemberRepository
import dev.coodie.api.domain.member.exception.MemberNotFoundException
import dev.coodie.api.domain.post.domain.Post
import dev.coodie.api.domain.post.domain.PostRepository
import dev.coodie.api.domain.post.domain.Series
import dev.coodie.api.domain.post.domain.SeriesRepository
import dev.coodie.api.domain.post.dto.PostCreateRequest
import dev.coodie.api.domain.post.dto.PostCreateResponse
import dev.coodie.api.domain.post.exception.PostSlugDuplicateException
import dev.coodie.api.domain.post.exception.SeriesNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class PostService(
    private val postRepository: PostRepository,
    private val seriesRepository: SeriesRepository,
    private val memberRepository: MemberRepository
) {
    
    @Transactional
    fun createPost(request: PostCreateRequest): PostCreateResponse {
        val (title, markdownBody, slug, seriesId, authorId) = request

        if (postRepository.existsBySlugAndAuthorId(slug, authorId)) {
            throw PostSlugDuplicateException()
        }

        if (!memberRepository.existsById(authorId)) {
            throw MemberNotFoundException()
        }

        var foundSeries: Series? = null
        if (seriesId != null) {
            foundSeries = (seriesRepository.findByIdOrNull(seriesId)
                ?: throw SeriesNotFoundException())
        }

        val post = Post(title, markdownBody, slug, foundSeries, authorId)
        val savedPost = postRepository.save(post)

        return PostCreateResponse(savedPost)
    }
}

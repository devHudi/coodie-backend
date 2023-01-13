package dev.coodie.api.domain.post.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun existsBySlugAndAuthorId(slug: String, authorId: Long): Boolean
    fun findByAuthorIdAndSlug(authorId: Long, slug: String): Post?
}

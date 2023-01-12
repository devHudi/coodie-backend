package dev.coodie.api.domain.post.dto

import dev.coodie.api.domain.post.domain.Post

data class PostCreateRequest(
    val title: String,
    val markdownBody: String,
    val slug: String,
    val seriesId: Long?,
    val authorId: Long
)

data class PostCreateResponse(
    val id: Long,
    val title: String,
    val htmlBody: String,
    val slug: String,
    val seriesId: Long?,
    val authorId: Long
) {
    constructor(post: Post) : this(
        post.id,
        post.title,
        post.htmlBody,
        post.slug,
        post.series?.id,
        post.authorId
    )
}

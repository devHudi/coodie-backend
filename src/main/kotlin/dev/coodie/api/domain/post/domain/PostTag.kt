package dev.coodie.api.domain.post.domain

import dev.coodie.api.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class PostTag(
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    val tag: Tag
) : BaseEntity()

package dev.coodie.api.domain.post.domain

import dev.coodie.api.domain.BaseEntity
import javax.persistence.Entity

@Entity
class Tag(
    val name: String,
    val memberId: Long
) : BaseEntity()

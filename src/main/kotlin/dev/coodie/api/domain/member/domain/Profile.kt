package dev.coodie.api.domain.member.domain

import dev.coodie.api.domain.BaseEntity
import javax.persistence.Entity

@Entity
class Profile(
    val profileImageUrl: String,
    val bio: String,
) : BaseEntity()

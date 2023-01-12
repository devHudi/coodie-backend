package dev.coodie.api.domain.member.domain

import dev.coodie.api.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class Profile(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,
    val profileImageUrl: String? = null,
    val bio: String = ""
) : BaseEntity()

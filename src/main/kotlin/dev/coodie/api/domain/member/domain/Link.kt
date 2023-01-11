package dev.coodie.api.domain.member.domain

import dev.coodie.api.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Link(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    val profile: Profile,
    val type: LinkType,
    val url: String
) : BaseEntity()

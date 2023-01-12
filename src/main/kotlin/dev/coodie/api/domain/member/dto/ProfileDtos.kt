package dev.coodie.api.domain.member.dto

import dev.coodie.api.domain.member.domain.Member
import dev.coodie.api.domain.member.domain.Profile

data class ProfileResponse(
    val profileImageUrl: String?,
    val bio: String,
    val memberDisplayName: String
) {
    constructor(profile: Profile, member: Member) : this(
        profile.profileImageUrl,
        profile.bio,
        member.displayName
    )
}

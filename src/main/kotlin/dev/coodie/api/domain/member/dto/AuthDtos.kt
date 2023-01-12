package dev.coodie.api.domain.member.dto

import dev.coodie.api.domain.member.domain.Member

data class MemberJoinRequest(
    val email: String,
    val username: String,
    val displayName: String,
    val password: String
)

data class MemberJoinResponse(
    val id: Long,
    val email: String,
    val username: String,
    val displayName: String
) {
    constructor(member: Member) : this(member.id, member.email, member.username, member.displayName)
}

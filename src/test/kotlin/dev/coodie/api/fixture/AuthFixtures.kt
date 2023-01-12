package dev.coodie.api.fixture

import dev.coodie.api.domain.member.dto.MemberJoinRequest
import dev.coodie.api.domain.member.dto.MemberJoinResponse

fun createMemberJoinRequest(
    email: String = EMAIL,
    username: String = USERNAME,
    displayName: String = DISPLAY_NAME,
    password: String = PASSWORD
) = MemberJoinRequest(email, username, displayName, password)

fun createMemberJoinResponse(
    id: Long = 0L,
    email: String = EMAIL,
    username: String = USERNAME,
    displayName: String = DISPLAY_NAME,
) = MemberJoinResponse(id, email, username, displayName)

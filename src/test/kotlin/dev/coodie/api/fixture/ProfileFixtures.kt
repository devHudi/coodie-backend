package dev.coodie.api.fixture

import dev.coodie.api.domain.member.domain.Member
import dev.coodie.api.domain.member.domain.Profile

const val BIO = "꾸준히, 의미있는 학습을 기록하기 위한 공간입니다."

fun createProfile(
    member: Member = createMember(),
    profileImageUrl: String? = null,
    bio: String = BIO
) = Profile(member, profileImageUrl, bio)

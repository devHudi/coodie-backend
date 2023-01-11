package dev.coodie.api.fixture

import dev.coodie.api.domain.member.domain.Member

const val EMAIL = "member1@email.com"
const val USERNAME = "member1"
const val DISPLAY_NAME = "멤버1"
const val PASSWORD = "PASSWORD"

fun createMember(
    email: String = EMAIL,
    username: String = USERNAME,
    displayName: String = DISPLAY_NAME,
    password: String = PASSWORD
) = Member(email, username, displayName, password)

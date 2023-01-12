package dev.coodie.api.domain.member.domain

import dev.coodie.api.domain.BaseEntity
import dev.coodie.api.domain.member.exception.MemberDisplayNameLengthException
import dev.coodie.api.domain.member.exception.MemberEmailFormatException
import dev.coodie.api.domain.member.exception.MemberUsernameFormatException
import dev.coodie.api.domain.member.exception.MemberUsernameLengthException
import dev.coodie.api.global.security.sha256
import javax.persistence.Entity

private const val EMAIL_REGEX = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
private const val USERNAME_REGEX = "\\w*"
private const val USERNAME_MIN_LENGTH = 1
private const val USERNAME_MAX_LENGTH = 30
private const val DISPLAY_NAME_MIX_LENGTH = 1
private const val DISPLAY_NAME_MAX_LENGTH = 20

@Entity
class Member(
    val email: String,
    val username: String,
    val displayName: String,
    password: String
) : BaseEntity() {

    val password: String = sha256(password)

    init {
        validateEmailFormat()
        validateUsernameFormat()
        validateUsernameLength()
        validateDisplayNameLength()
    }

    private fun validateEmailFormat() {
        val isValidEmailFormat = EMAIL_REGEX.toRegex().matches(email)
        if (!isValidEmailFormat) {
            throw MemberEmailFormatException()
        }
    }

    private fun validateUsernameFormat() {
        val isValidUsernameFormat = USERNAME_REGEX.toRegex().matches(username)
        if (!isValidUsernameFormat) {
            throw MemberUsernameFormatException()
        }
    }

    private fun validateUsernameLength() {
        val isValidUsernameLength = username.length in (USERNAME_MIN_LENGTH..USERNAME_MAX_LENGTH)
        if (!isValidUsernameLength) {
            throw MemberUsernameLengthException()
        }
    }

    private fun validateDisplayNameLength() {
        val isValidDisplayNameLength = displayName.length in (DISPLAY_NAME_MIX_LENGTH..DISPLAY_NAME_MAX_LENGTH)
        if (!isValidDisplayNameLength) {
            throw MemberDisplayNameLengthException()
        }
    }
}

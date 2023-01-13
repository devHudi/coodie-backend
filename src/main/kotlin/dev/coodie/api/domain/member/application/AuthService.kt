package dev.coodie.api.domain.member.application

import dev.coodie.api.domain.member.domain.Member
import dev.coodie.api.domain.member.domain.MemberJoinedEvent
import dev.coodie.api.domain.member.domain.MemberRepository
import dev.coodie.api.domain.member.dto.MemberJoinRequest
import dev.coodie.api.domain.member.dto.MemberJoinResponse
import dev.coodie.api.domain.member.exception.MemberEmailDuplicateException
import dev.coodie.api.domain.member.exception.MemberUsernameDuplicateException
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val eventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun join(memberJoinRequest: MemberJoinRequest): MemberJoinResponse {
        val (email, username, displayName, password) = memberJoinRequest
        val member = Member(email, username, displayName, password)

        if (memberRepository.existsByEmail(email)) {
            throw MemberEmailDuplicateException()
        }

        if (memberRepository.existsByUsername(username)) {
            throw MemberUsernameDuplicateException()
        }

        val savedMember = memberRepository.save(member)
        eventPublisher.publishEvent(MemberJoinedEvent(savedMember.id))

        return MemberJoinResponse(savedMember)
    }
}

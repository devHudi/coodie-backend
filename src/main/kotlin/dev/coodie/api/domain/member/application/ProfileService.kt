package dev.coodie.api.domain.member.application

import dev.coodie.api.domain.member.domain.MemberJoinedEvent
import dev.coodie.api.domain.member.domain.MemberRepository
import dev.coodie.api.domain.member.domain.Profile
import dev.coodie.api.domain.member.domain.ProfileRepository
import dev.coodie.api.domain.member.dto.ProfileResponse
import dev.coodie.api.domain.member.exception.MemberNotFoundException
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfileService(
    val profileRepository: ProfileRepository,
    val memberRepository: MemberRepository
) {
    @EventListener
    fun createProfile(memberJoinedEvent: MemberJoinedEvent): ProfileResponse {
        val memberId = memberJoinedEvent.memberId
        val foundMember = memberRepository.findByIdOrNull(memberId)
            ?: throw MemberNotFoundException()

        val emptyProfile = Profile(foundMember)
        val savedProfile = profileRepository.save(emptyProfile)

        return ProfileResponse(savedProfile, foundMember)
    }
}

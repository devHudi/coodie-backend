package dev.coodie.api.domain.member.application

import dev.coodie.api.domain.member.domain.MemberJoinedEvent
import dev.coodie.api.domain.member.domain.MemberRepository
import dev.coodie.api.domain.member.domain.ProfileRepository
import dev.coodie.api.fixture.DISPLAY_NAME
import dev.coodie.api.fixture.createMember
import dev.coodie.api.fixture.createProfile
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.repository.findByIdOrNull

class ProfileServiceTest : BehaviorSpec({
    val profileRepository = mockk<ProfileRepository>()
    val memberRepository = mockk<MemberRepository>()
    val profileService = ProfileService(profileRepository, memberRepository)

    Given("회원 프로필을 생성할 때") {
        When("MemberJoinedEvent를 전달하면") {
            every { profileRepository.save(any()) } returns createProfile(bio = "")
            every { memberRepository.findByIdOrNull(any()) } returns createMember()

            val event = MemberJoinedEvent(1)
            val profile = profileService.createProfile(event)

            Then("프로필이 생성된다.") {
                profile.profileImageUrl shouldBe null
                profile.bio shouldBe ""
                profile.memberDisplayName shouldBe DISPLAY_NAME
            }
        }
    }
})

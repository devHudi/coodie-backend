package dev.coodie.api.domain.member.application

import dev.coodie.api.domain.member.domain.MemberRepository
import dev.coodie.api.domain.member.exception.MemberEmailDuplicateException
import dev.coodie.api.domain.member.exception.MemberUsernameDuplicateException
import dev.coodie.api.fixture.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.context.ApplicationEventPublisher

class AuthServiceTest : BehaviorSpec({
    val memberRepository = mockk<MemberRepository>(relaxed = true)
    val eventPublisher = mockk<ApplicationEventPublisher>(relaxed = true)
    val authService = AuthService(memberRepository, eventPublisher)

    Given("회원가입을 시도할 떄") {
        When("정상적인 정보를 전달하면") {
            every { memberRepository.save(createMember()) } returns createMember()

            val memberJoinRequest = createMemberJoinRequest()
            val memberJoinResponse = authService.join(memberJoinRequest)

            Then("회원가입이 완료된다.") {
                memberJoinResponse.email shouldBe EMAIL
                memberJoinResponse.username shouldBe USERNAME
                memberJoinResponse.displayName shouldBe DISPLAY_NAME
            }
        }

        When("중복된 이메일을 전달하면") {
            every { memberRepository.existsByEmail(any()) } returns true

            Then("MemberEmailDuplicateException 예외가 발생한다.") {
                shouldThrow<MemberEmailDuplicateException> {
                    authService.join(createMemberJoinRequest())
                }
            }
        }

        When("중복된 사용자명을 전달하면") {
            every { memberRepository.existsByEmail(any()) } returns false
            every { memberRepository.existsByUsername(any()) } returns true

            Then("MemberUsernameDuplicateException 예외가 발생한다.") {
                shouldThrow<MemberUsernameDuplicateException> {
                    authService.join(createMemberJoinRequest())
                }
            }
        }
    }
})

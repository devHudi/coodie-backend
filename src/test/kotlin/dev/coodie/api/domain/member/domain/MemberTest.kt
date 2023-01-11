package dev.coodie.api.domain.member.domain

import dev.coodie.api.domain.member.exception.MemberDisplayNameLengthException
import dev.coodie.api.domain.member.exception.MemberEmailFormatException
import dev.coodie.api.domain.member.exception.MemberUsernameFormatException
import dev.coodie.api.domain.member.exception.MemberUsernameLengthException
import dev.coodie.api.fixture.createMember
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MemberTest : BehaviorSpec({
    Given("Member가 생성되었을 때") {
        val member = createMember()

        When("패스워드를 가져오면") {
            val actual = member.password

            Then("Sha256으로 암호화 되어있다.") {
                actual shouldBe "0be64ae89ddd24e225434de95d501711339baeee18f009ba9b4369af27d30d60"
            }
        }
    }

    Given("Member 파라미터 유효성 검사 시") {
        When("잘못된 패스워드를 전달했을 때") {
            Then("MemberEmailFormatException 예외가 발생한다.") {
                forAll(
                    row(""),
                    row("devhudi"),
                    row("devhudi@"),
                    row("devhudi@gmail"),
                    row("devhudi@gmail."),
                    row("@gmail.com"),
                    row("gmail.com"),
                ) { given ->
                    shouldThrow<MemberEmailFormatException> {
                        createMember(email = given)
                    }
                }
            }
        }

        When("username에 알파벳, 숫자, 언더스코어를 제외한 문자가 포함되었을 때") {
            Then("MemberUsernameFormatException 예외가 발생한다.") {
                forAll(
                    row("devhudi!"),
                    row("dev.hudi"),
                    row("dev hudi")
                ) { given ->
                    shouldThrow<MemberUsernameFormatException> {
                        createMember(username = given)
                    }
                }
            }
        }

        When("username의 길이가 1미만, 30초과일 때") {
            Then("MemberUsernameFormatException 예외가 발생한다.") {
                forAll(
                    row(""),
                    row("a".repeat(31))
                ) { given ->
                    shouldThrow<MemberUsernameLengthException> {
                        createMember(username = given)
                    }
                }
            }
        }

        When("displayName의 길이가 1미만, 20초과일 때") {
            Then("MemberDisplayNameLengthException 예외가 발생한다.") {
                forAll(
                    row(""),
                    row("a".repeat(21))
                ) { given ->
                    shouldThrow<MemberDisplayNameLengthException> {
                        createMember(displayName = given)
                    }
                }
            }
        }
    }
})

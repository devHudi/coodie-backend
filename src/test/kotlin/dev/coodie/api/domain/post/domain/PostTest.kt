package dev.coodie.api.domain.post.domain

import dev.coodie.api.domain.post.exception.PostBodyEmptyException
import dev.coodie.api.domain.post.exception.PostTitleEmptyException
import dev.coodie.api.domain.post.exception.PostTitleLengthException
import dev.coodie.api.fixture.createPost
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PostTest : BehaviorSpec({
    Given("Post 파라미터 유효성 검사 시") {
        When("title이 공백일 떄") {
            Then("PostTitleEmptyException 예외가 발생한다.") {
                forAll(
                    row(""),
                    row(" "),
                    row("   "),
                ) { given ->
                    shouldThrow<PostTitleEmptyException> {
                        createPost(title = given)
                    }
                }
            }
        }

        When("title의 글자수가 100자를 초과했을 때") {
            val actual = "a".repeat(101)

            Then("PostTitleLengthException 예외가 발생한다.") {
                shouldThrow<PostTitleLengthException> {
                    createPost(title = actual)
                }
            }
        }

        When("body이 공백일 때") {
            Then("PostBodyEmptyException 예외가 발생한다.") {
                forAll(
                    row(""),
                    row(" "),
                    row("   "),
                ) { given ->
                    shouldThrow<PostBodyEmptyException> {
                        createPost(markdownBody = given)
                    }
                }
            }
        }
    }

    Given("포스트가 생성 되었을 때") {
        val post = createPost()

        When("htmlBody 필드를 가져오면") {
            val actual = post.htmlBody

            Then("HTML으로 렌더되었다.") {
                actual shouldBe """
                    <h2>제목1</h2>
                    <h3>부제목1</h3>
                    <ul>
                    <li>리스트1</li>
                    <li>리스트2</li>
                    </ul>
                    """.trimIndent() + "\n"
            }
        }
    }
})

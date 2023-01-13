package dev.coodie.api.domain.post.application

import dev.coodie.api.domain.member.domain.MemberRepository
import dev.coodie.api.domain.member.exception.MemberNotFoundException
import dev.coodie.api.domain.post.domain.PostRepository
import dev.coodie.api.domain.post.domain.SeriesRepository
import dev.coodie.api.domain.post.exception.PostNotFoundException
import dev.coodie.api.domain.post.exception.PostSlugDuplicateException
import dev.coodie.api.fixture.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.repository.findByIdOrNull

class PostServiceTest : BehaviorSpec({
    val postRepository = mockk<PostRepository>()
    val seriesRepository = mockk<SeriesRepository>()
    val memberRepository = mockk<MemberRepository>(relaxed = true)
    val postService = PostService(postRepository, seriesRepository, memberRepository)

    Given("포스트를 생성할 때") {
        When("정상적인 정보를 전달하면") {
            every { postRepository.save(any()) } returns createPost()
            every { seriesRepository.findByIdOrNull(any()) } returns createSeries()
            every { postRepository.existsBySlugAndAuthorId(any(), any()) } returns false
            every { memberRepository.existsById(any()) } returns true

            val request = createPostCreateRequest()
            val response = postService.createPost(request)

            Then("포스트가 생성된다.") {
                response.title shouldBe TITLE
                response.slug shouldBe "spring-basic-01"
                response.htmlBody shouldBe HTML_BODY
            }
        }

        When("동일한 유저가 중복된 slug를 생성했을 때") {
            every { postRepository.existsBySlugAndAuthorId(any(), any()) } returns true

            Then("PostSlugDuplicateException 예외를 던진다.") {
                shouldThrow<PostSlugDuplicateException> {
                    postService.createPost(createPostCreateRequest())
                }
            }
        }

        When("authorId에 해당하는 회원이 존재하지 않다면") {
            every { postRepository.existsBySlugAndAuthorId(any(), any()) } returns false
            every { memberRepository.existsById(any()) } returns false

            Then("MemberNotFoundException 예외를 던진다.") {
                shouldThrow<MemberNotFoundException> {
                    postService.createPost(createPostCreateRequest())
                }
            }
        }
    }

    Given("포스트를 조회할 때") {
        When("사용자명과 slug를 전달하면") {
            every { postRepository.findByAuthorIdAndSlug(any(), any()) } returns createPost()

            val postResponse = postService.getPost(USERNAME, SLUG)

            Then("포스트를 조회할 수 있다.") {
                postResponse.title shouldBe TITLE
                postResponse.htmlBody shouldBe HTML_BODY
            }
        }

        When("존재하지 않은 사용자명을 전달한다면") {
            every { memberRepository.findByUsername(any()) } returns null

            Then("MemberNotFoundException 예외를 던진다.") {
                shouldThrow<MemberNotFoundException> {
                    postService.getPost(USERNAME, SLUG)
                }
            }
        }

        When("존재하지 않은 slug를 전달한다면") {
            every { memberRepository.findByUsername(any()) } returns createMember()
            every { postRepository.findByAuthorIdAndSlug(any(), any()) } returns null

            Then("PostNotFoundException 예외를 던진다.") {
                shouldThrow<PostNotFoundException> {
                    postService.getPost(USERNAME, SLUG)
                }
            }
        }
    }
})

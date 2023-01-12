package dev.coodie.api.domain.post.domain

import dev.coodie.api.domain.BaseEntity
import dev.coodie.api.domain.post.exception.PostBodyEmptyException
import dev.coodie.api.domain.post.exception.PostSlugEmptyException
import dev.coodie.api.domain.post.exception.PostTitleEmptyException
import dev.coodie.api.domain.post.exception.PostTitleLengthException
import org.commonmark.node.Node
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

private const val TITLE_MAX_LENGTH = 100
private const val ALLOWED_SLUG_PATTERN = "[a-zA-Z0-9ㄱ-ㅎ가-힣-]"

@Entity
class Post(
    val title: String,
    val markdownBody: String,
    slug: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    val series: Series,
    val authorId: Long
) : BaseEntity() {
    val slug = convertSlug(slug)
    val htmlBody: String
        get() = renderMarkdown()

    init {
        validateTitleBlank()
        validateTitleLength()
        validateBodyBlank()
        validateSlugBlank()
    }

    private fun validateTitleBlank() {
        if (title.isBlank()) {
            throw PostTitleEmptyException()
        }
    }

    private fun validateTitleLength() {
        if (title.length > TITLE_MAX_LENGTH) {
            throw PostTitleLengthException()
        }
    }

    private fun validateBodyBlank() {
        if (markdownBody.isBlank()) {
            throw PostBodyEmptyException()
        }
    }

    private fun validateSlugBlank() {
        if (slug.isBlank()) {
            throw PostSlugEmptyException()
        }
    }

    private fun renderMarkdown(): String {
        val parser: Parser = Parser.builder().build()
        val document: Node = parser.parse(markdownBody)
        val renderer = HtmlRenderer.builder().build()
        return renderer.render(document)
    }

    private fun convertSlug(original: String): String {
        return original.replace(" ", "-")
            .filter { ALLOWED_SLUG_PATTERN.toRegex().matches("$it") }
    }
}

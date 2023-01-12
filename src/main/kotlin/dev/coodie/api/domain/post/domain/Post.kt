package dev.coodie.api.domain.post.domain

import dev.coodie.api.domain.BaseEntity
import dev.coodie.api.domain.member.domain.Member
import dev.coodie.api.domain.post.exception.PostBodyEmptyException
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

@Entity
class Post(
    val title: String,
    val markdownBody: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    val series: Series,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val author: Member
) : BaseEntity() {
    val htmlBody: String
        get() = renderMarkdown()

    init {
        validateTitleBlank()
        validateTitleLength()
        validateBodyBlank()
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

    private fun renderMarkdown(): String {
        val parser: Parser = Parser.builder().build()
        val document: Node = parser.parse(markdownBody)
        val renderer = HtmlRenderer.builder().build()
        return renderer.render(document)
    }
}

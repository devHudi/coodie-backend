package dev.coodie.api.fixture

import dev.coodie.api.domain.post.domain.Post
import dev.coodie.api.domain.post.domain.Series
import dev.coodie.api.domain.post.dto.PostCreateRequest
import dev.coodie.api.domain.post.dto.PostCreateResponse

const val TITLE = "스프링 기초 포스팅"
const val BODY = """
## 제목1
### 부제목1

- 리스트1
- 리스트2
"""
const val HTML_BODY = """<h2>제목1</h2>
<h3>부제목1</h3>
<ul>
<li>리스트1</li>
<li>리스트2</li>
</ul>

"""

const val SLUG = "spring-basic-01"

fun createPost(
    title: String = TITLE,
    markdownBody: String = BODY,
    slug: String = SLUG,
    series: Series = createSeries(),
    memberId: Long = 1L
) = Post(title, markdownBody, slug, series, memberId)

fun createPostCreateRequest(
    title: String = TITLE,
    markdownBody: String = BODY,
    slug: String = SLUG,
    seriesId: Long = 1L,
    memberId: Long = 1L
) = PostCreateRequest(title, markdownBody, slug, seriesId, memberId)

fun createPostCreateResponse(
    id: Long = 1L,
    title: String = TITLE,
    htmlBody: String = HTML_BODY,
    slug: String = SLUG,
    seriesId: Long = 1L,
    memberId: Long = 1L
) = PostCreateResponse(id, title, htmlBody, slug, seriesId, memberId)

const val SERIES_NAME = "스프링 기초 시리즈"

fun createSeries(
    name: String = SERIES_NAME,
    memberId: Long = 1L
) = Series(name, memberId)

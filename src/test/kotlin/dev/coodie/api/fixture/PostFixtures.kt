package dev.coodie.api.fixture

import dev.coodie.api.domain.post.domain.Post
import dev.coodie.api.domain.post.domain.Series

const val TITLE = "스프링 기초 포스팅"
const val BODY = """
## 제목1
### 부제목1

- 리스트1
- 리스트2
"""

fun createPost(
    title: String = TITLE,
    markdownBody: String = BODY,
    series: Series = createSeries(),
    memberId: Long = 1L
) = Post(title, markdownBody, series, memberId)

const val SERIES_NAME = "스프링 기초 시리즈"

fun createSeries(
    name: String = SERIES_NAME
) = Series(SERIES_NAME)

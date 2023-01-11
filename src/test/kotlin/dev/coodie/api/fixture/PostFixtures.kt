package dev.coodie.api.fixture

import dev.coodie.api.domain.member.domain.Member
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
    member: Member = createMember()
) = Post(title, markdownBody, series, member)

const val SERIES_NAME = "스프링 기초 시리즈"

fun createSeries(
    name: String = SERIES_NAME
) = Series(SERIES_NAME)
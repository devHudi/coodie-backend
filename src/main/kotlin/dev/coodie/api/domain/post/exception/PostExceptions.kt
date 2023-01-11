package dev.coodie.api.domain.post.exception

class PostTitleEmptyException : IllegalArgumentException(
    "포스트 제목은 공백일 수 없습니다."
)

class PostTitleLengthException : IllegalArgumentException(
    "포스트 제목은 최대 100자까지 입력할 수 있습니다."
)

class PostBodyEmptyException : IllegalArgumentException(
    "포스트 본문은 공백일 수 없습니다."
)

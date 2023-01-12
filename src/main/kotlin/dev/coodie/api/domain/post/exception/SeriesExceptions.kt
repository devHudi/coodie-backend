package dev.coodie.api.domain.post.exception

class SeriesNotFoundException : IllegalArgumentException(
    "시리즈를 찾을 수 없습니다."
)

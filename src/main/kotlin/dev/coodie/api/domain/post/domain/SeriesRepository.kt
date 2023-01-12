package dev.coodie.api.domain.post.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SeriesRepository : JpaRepository<Series, Long>

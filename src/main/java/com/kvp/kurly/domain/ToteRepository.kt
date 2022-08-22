package com.kvp.kurly.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ToteRepository: JpaRepository<Tote, Long> {

    fun existsByCode(code: String): Boolean

    fun findByCode(code: String): Tote?
}
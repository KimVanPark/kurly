package com.kvp.kurly.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PickingRepository: JpaRepository<Picking, Long> {
}
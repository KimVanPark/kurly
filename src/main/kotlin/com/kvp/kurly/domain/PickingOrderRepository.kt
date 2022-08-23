package com.kvp.kurly.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PickingOrderRepository: JpaRepository<PickingOrder, Long> {

    fun findFirstByWorkerIdIsNullOrderById(): PickingOrder?
}
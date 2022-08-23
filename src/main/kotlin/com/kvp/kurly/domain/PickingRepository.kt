package com.kvp.kurly.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface PickingRepository : JpaRepository<Picking, Long> {

    fun findFirstByWorkerOrderByPickingAtDesc(worker: Worker): Picking?

    @Query(
        """
            SELECT p FROM Picking p
            WHERE p.worker = :worker AND p.pickingAt >= :from AND p.pickingAt < :to
        """
    )
    fun findHistories(worker: Worker, from: LocalDateTime, to: LocalDateTime): List<Picking>

    fun existsByWorkerAndPickingAtAfter(worker: Worker, now: LocalDateTime): Boolean
}

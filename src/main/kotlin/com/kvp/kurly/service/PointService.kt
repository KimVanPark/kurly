package com.kvp.kurly.service

import com.kvp.kurly.domain.PickingRepository
import com.kvp.kurly.domain.PointHistoryRepository
import com.kvp.kurly.domain.PointReason
import com.kvp.kurly.domain.Worker
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PointService(
    private val pickingRepository: PickingRepository,
    private val pointHistoryRepository: PointHistoryRepository,
    private val workerService: WorkerService,
) {
    
    fun adjustBeforePicking(worker: Worker) {
        val now = LocalDateTime.now()

        // 오늘 피킹 기록이 있으면, 포인트 조정 로직은 동작하지 않음
        if (pickingRepository.existsByPickingAtAfter(now.toLocalDate().atStartOfDay())) {
            return
        }

        plusByPerfect(worker, now)
        minusByPeriod(worker, now)
    }

    private fun plusByPerfect(worker: Worker, now: LocalDateTime) {
        val yesterday = now.toLocalDate().minusDays(1)

        if (pointHistoryRepository.existsByWorkerIdAndReasonAndDate(worker.getId(), PointReason.MISTAKE, yesterday)) {
            return
        }

        val yesterdayPickingHistories = pickingRepository.findHistories(
            worker = worker,
            from = yesterday.atStartOfDay(),
            to = yesterday.plusDays(1).atStartOfDay(),
        )
        
        if (yesterdayPickingHistories.isEmpty()) {
            return
        }

        // 어제 작업한 피킹에서 이슈가 없으면, 플러스
        val pickingOrderItemCount = yesterdayPickingHistories.map { it.pickingOrderItem }.toSet().size
        val plusPoint = if (pickingOrderItemCount > 10) 10 else pickingOrderItemCount

        workerService.addPoint(worker.getId(), plusPoint, PointReason.PERFECT)
    }

    private fun minusByPeriod(worker: Worker, now: LocalDateTime) {
        val lastPickingHistory = pickingRepository.findFirstByWorkerOrderByPickingAtDesc(worker) ?: return

        // 한달이 지난 작업자
        if (lastPickingHistory.pickingAt.isAfter(now.plusMonths(1))) {
            val changedPoint = worker.getPoint() - Worker.INIT_POINT

            if (changedPoint > 0) {
                workerService.subtractPoint(worker.getId(), changedPoint, PointReason.REST)
            }
        }

        // 2주일이 지난 작업자
        if (lastPickingHistory.pickingAt.isAfter(now.plusDays(14))) {
            val tempPoint = worker.getPoint() - 50

            if (tempPoint >= Worker.INIT_POINT) {
                workerService.subtractPoint(worker.getId(), 50, PointReason.REST)
            } else {
                val changedPoint = worker.getPoint() - Worker.INIT_POINT

                if (changedPoint > 0) {
                    workerService.subtractPoint(worker.getId(), changedPoint, PointReason.REST)
                }
            }
        }
    }
}
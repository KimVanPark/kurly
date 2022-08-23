package com.kvp.kurly.service

import com.kvp.kurly.domain.PickingRepository
import com.kvp.kurly.domain.PointHistoryRepository
import com.kvp.kurly.domain.PointReason
import com.kvp.kurly.domain.Worker
import com.kvp.kurly.dto.PointResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class PointService(
    private val pickingRepository: PickingRepository,
    private val pointHistoryRepository: PointHistoryRepository,
    private val workerService: WorkerService,
) {

    fun adjustBeforePicking(worker: Worker): List<PointResponse> {
        val now = LocalDateTime.now()

        // 오늘 피킹 기록이 있으면, 포인트 조정 로직은 동작하지 않음
        if (pickingRepository.existsByPickingAtAfter(now.toLocalDate().atStartOfDay())) {
            return listOf()
        }

        val plusResponse = plusByPerfect(worker, now)
        val minusResponse = minusByPeriod(worker, now)
        return listOfNotNull(plusResponse, minusResponse)
    }

    private fun plusByPerfect(worker: Worker, now: LocalDateTime): PointResponse? {
        val yesterday = now.toLocalDate().minusDays(1)

        if (pointHistoryRepository.existsByWorkerIdAndReasonAndDate(worker.getId(), PointReason.MISTAKE, yesterday)) {
            return null
        }

        val yesterdayPickingHistories = pickingRepository.findHistories(
            worker = worker,
            from = yesterday.atStartOfDay(),
            to = yesterday.plusDays(1).atStartOfDay(),
        )

        if (yesterdayPickingHistories.isEmpty()) {
            return null
        }

        // 어제 작업한 피킹에서 이슈가 없으면, 플러스
        val pickingOrderItemCount = yesterdayPickingHistories.map { it.pickingOrderItem }.toSet().size
        val plusPoint = if (pickingOrderItemCount > 10) 10 else pickingOrderItemCount

        workerService.addPoint(worker.getId(), plusPoint, PointReason.PERFECT)
        return PointResponse(plusPoint, PointReason.PERFECT)
    }

    private fun minusByPeriod(worker: Worker, now: LocalDateTime): PointResponse? {
        val lastPickingHistory = pickingRepository.findFirstByWorkerOrderByPickingAtDesc(worker) ?: return null

        // 한달이 지난 작업자
        if (lastPickingHistory.pickingAt.plusMonths(1).isBefore(now)) {
            val changedPoint = worker.getPoint() - Worker.INIT_POINT

            if (changedPoint > 0) {
                workerService.subtractPoint(worker.getId(), changedPoint, PointReason.REST)
                return PointResponse(-changedPoint, PointReason.REST)
            }
        }
        // 2주일이 지난 작업자
        else if (lastPickingHistory.pickingAt.plusDays(14).isBefore(now)) {
                val tempPoint = worker.getPoint() - 50

                if (tempPoint >= Worker.INIT_POINT) {
                    workerService.subtractPoint(worker.getId(), 50, PointReason.REST)
                    return PointResponse(-50, PointReason.REST)
                } else {
                    val changedPoint = worker.getPoint() - Worker.INIT_POINT

                    if (changedPoint > 0) {
                        workerService.subtractPoint(worker.getId(), changedPoint, PointReason.REST)
                        return PointResponse(-changedPoint, PointReason.REST)
                    }
                }
            }

        return null
    }
}

package com.kvp.kurly.service

import com.kvp.kurly.domain.Picking
import com.kvp.kurly.domain.PickingOrder
import com.kvp.kurly.domain.PickingOrderItem
import com.kvp.kurly.domain.PickingOrderItemRepository
import com.kvp.kurly.domain.PickingOrderRepository
import com.kvp.kurly.domain.PickingRepository
import com.kvp.kurly.domain.ToteRepository
import com.kvp.kurly.domain.Worker
import com.kvp.kurly.domain.WorkerRepository
import com.kvp.kurly.dto.PickingRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PickingService(
    private val pickingRepository: PickingRepository,
    private val toteRepository: ToteRepository,
    private val pickingOrderRepository: PickingOrderRepository,
    private val pickingOrderItemRepository: PickingOrderItemRepository,
    private val workerService: WorkerService,
) {

    @Transactional
    fun picking(pickingOrderId: Long, pickingOrderItemId: Long, request: PickingRequest): PickingOrderItem {
        val pickingOrder = pickingOrderRepository.findByIdOrNull(pickingOrderId)
            ?: throw IllegalArgumentException("Picking Order (ID: $pickingOrderId)를 찾을 수 없습니다.")
        val pickingOrderItem = pickingOrderItemRepository.findByIdOrNull(pickingOrderItemId)
            ?: throw IllegalArgumentException("Picking Order Item(ID: $pickingOrderItemId)를 찾을 수 없습니다.")
        val worker = workerService.find(request.workerId)

        checkPickingInput(pickingOrder, pickingOrderItem, worker, request)

        pickingRepository.save(
            Picking(
                worker = worker,
                tote = toteRepository.findByCode(request.toteCode)
                    ?: throw IllegalArgumentException("Tote Code(${request.toteCode})를 찾을 수 없습니다."),
                pickingOrderItem = pickingOrderItem,
                count = request.count,
            )
        )

        return pickingOrderItem
    }

    private fun checkPickingInput(
        pickingOrder: PickingOrder,
        pickingOrderItem: PickingOrderItem,
        worker: Worker,
        request: PickingRequest
    ) {
        if (pickingOrderItem.pickingOrder != pickingOrder) {
            throw IllegalArgumentException("피킹 주문서(ID: ${pickingOrder.id})의 피킹 주문 아이템이 아닙니다.")
        }

        if (pickingOrder.worker != worker) {
            throw IllegalArgumentException("피킹 주문서의 작업자와 요청 작업자가 다릅니다.")
        }

        if (pickingOrderItem.location.isSameCode(request.locationCode).not()) {
            throw IllegalArgumentException("피킹 위치가 올바르지 않습니다.")
        }

        if (pickingOrderItem.sku.isSameBarcode(request.skuBarcode).not()) {
            throw IllegalArgumentException("바코드가 올바르지 않습니다.")
        }

        checkPickingCount(pickingOrderItem, request.count)
    }
    
    private fun checkPickingCount(pickingOrderItem: PickingOrderItem, pickingCount: Int) {
        val pickedCount = pickingOrderItem.pickingHistories.fold(0) { sum: Int, current: Picking -> sum + current.count }
        val remainCount = pickingOrderItem.count - pickedCount

        if (remainCount < pickingCount) {
            throw IllegalArgumentException("피킹 수량이 올바르지 않습니다.")
        }
    }
}
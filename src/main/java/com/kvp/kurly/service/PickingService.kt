package com.kvp.kurly.service

import com.kvp.kurly.domain.Picking
import com.kvp.kurly.domain.PickingOrderItem
import com.kvp.kurly.domain.PickingOrderItemRepository
import com.kvp.kurly.domain.PickingRepository
import com.kvp.kurly.domain.ToteRepository
import com.kvp.kurly.dto.PickingRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PickingService(
    private val pickingRepository: PickingRepository,
    private val toteRepository: ToteRepository,
    private val pickingOrderItemRepository: PickingOrderItemRepository,
) {

    @Transactional
    fun picking(pickingOrderItemId: Long, request: PickingRequest): PickingOrderItem {
        val pickingOrderItem = pickingOrderItemRepository.findByIdOrNull(pickingOrderItemId)
            ?: throw IllegalArgumentException("Picking Order Item(ID: ${request.toteCode})를 찾을 수 없습니다.")

        checkPickingInput(pickingOrderItem, request)

        pickingRepository.save(
            Picking(
                workerId = 0L, // FIXME
                tote = toteRepository.findByCode(request.toteCode)
                    ?: throw IllegalArgumentException("Tote Code(${request.toteCode})를 찾을 수 없습니다."),
                pickingOrderItem = pickingOrderItem,
                count = request.count,
            )
        )

        return pickingOrderItem
    }

    private fun checkPickingInput(pickingOrderItem: PickingOrderItem, request: PickingRequest) {
        if (pickingOrderItem.location.getCode() != request.locationCode) {
            throw IllegalArgumentException("피킹 위치가 올바르지 않습니다.")
        }

        if (pickingOrderItem.sku.getBarcode() != request.skuBarcode) {
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
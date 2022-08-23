package com.kvp.kurly.dto

import com.kvp.kurly.domain.Picking
import com.kvp.kurly.domain.PickingOrderItem

data class PickingResponse(
    val pickingOrderId: Long,
    val pickingOrderItemId: Long,
    val count: Int,
    val pickingCount: Int,
) {

    companion object {
        fun from(pickingOrderItem: PickingOrderItem): PickingResponse =
            PickingResponse(
                pickingOrderId = pickingOrderItem.pickingOrder.id,
                pickingOrderItemId = pickingOrderItem.id,
                count = pickingOrderItem.count,
                pickingCount = pickingOrderItem.pickingHistories.fold(0) { sum: Int, current: Picking -> sum + current.count }
            )
    }
}
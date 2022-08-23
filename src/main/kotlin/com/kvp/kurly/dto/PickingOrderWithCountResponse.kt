package com.kvp.kurly.dto

import com.kvp.kurly.domain.Picking
import com.kvp.kurly.domain.PickingOrder
import com.kvp.kurly.domain.PickingOrderItem

data class PickingOrderWithCountResponse(
    val id: Long,
    val items: List<PickingOrderItemWithCountResponse>,
) {

    companion object {
        fun from(pickingOrder: PickingOrder) =
            PickingOrderWithCountResponse(
                id = pickingOrder.id,
                items = pickingOrder.items.map { PickingOrderItemWithCountResponse.from(it) }
            )
    }
}

data class PickingOrderItemWithCountResponse(
    val id: Long,
    val location: LocationResponse,
    val sku: SkuResponse,
    val totalCount: Int,
    val pickingCount: Int,
) {

    companion object {
        fun from(pickingOrderItem: PickingOrderItem) =
            PickingOrderItemWithCountResponse(
                id = pickingOrderItem.id,
                location = LocationResponse.from(pickingOrderItem.location),
                sku = SkuResponse.from(pickingOrderItem.sku),
                totalCount = pickingOrderItem.count,
                pickingCount = pickingOrderItem.pickingHistories.fold(0) { sum: Int, current: Picking -> sum + current.count }
            )
    }
}
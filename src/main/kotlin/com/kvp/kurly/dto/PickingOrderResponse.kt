package com.kvp.kurly.dto

import com.kvp.kurly.domain.PickingOrder
import com.kvp.kurly.domain.PickingOrderItem

data class PickingOrderResponse(
    val id: Long,
    val items: List<PickingOrderItemResponse>,
) {

    companion object {
        fun from(pickingOrder: PickingOrder) =
            PickingOrderResponse(
                id = pickingOrder.id,
                items = pickingOrder.items.map { PickingOrderItemResponse.from(it) }
            )
    }
}

data class PickingOrderItemResponse(
    val id: Long,
    val location: LocationResponse,
    val sku: SkuResponse,
    val count: Int,
) {

    companion object {
        fun from(pickingOrderItem: PickingOrderItem) =
            PickingOrderItemResponse(
                id = pickingOrderItem.id,
                location = LocationResponse.from(pickingOrderItem.location),
                sku = SkuResponse.from(pickingOrderItem.sku),
                count = pickingOrderItem.count,
            )
    }
}
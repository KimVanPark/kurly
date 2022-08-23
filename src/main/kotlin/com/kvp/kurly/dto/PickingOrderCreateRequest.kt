package com.kvp.kurly.dto

data class PickingOrderCreateRequest(
    val items: List<PickingOrderItemCreateRequest>
)

data class PickingOrderItemCreateRequest(
    val locationCode: String,
    val skuCode: String,
    val count: Int,
)
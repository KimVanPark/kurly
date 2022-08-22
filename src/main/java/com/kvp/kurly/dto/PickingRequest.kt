package com.kvp.kurly.dto

data class PickingRequest(
    val workerId: Long,
    val locationCode: String,
    val skuBarcode: String,
    val toteCode: String,
    val count: Int,
)

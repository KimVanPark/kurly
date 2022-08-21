package com.kvp.kurly.dto

data class PickingRequest(
    val workerName: String,
    val toteCode: String,
    val count: Int,
)

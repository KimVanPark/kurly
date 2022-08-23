package com.kvp.kurly.dto

import com.kvp.kurly.domain.PointReason

data class PointResponse(
    val point: Int,
    val pointReason: PointReason
)

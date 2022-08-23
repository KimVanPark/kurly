package com.kvp.kurly.dto

import com.kvp.kurly.domain.Tote

data class ToteResponse(
    val id: Long,
    val code: String,
) {

    companion object {
        fun from(tote: Tote): ToteResponse =
            ToteResponse(
                id = tote.id,
                code = tote.code,
            )
    }
}
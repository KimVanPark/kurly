package com.kvp.kurly.service

import com.kvp.kurly.domain.Tote
import com.kvp.kurly.domain.ToteRepository
import com.kvp.kurly.dto.ToteCreateRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ToteService(
    private val toteRepository: ToteRepository,
) {

    fun create(request: ToteCreateRequest): Tote {
        if (toteRepository.existsByCode(request.code)) {
            throw IllegalArgumentException("Tote 코드(${request.code})가 이미 존재합니다.")
        }

        return toteRepository.save(
            Tote(code = request.code)
        )
    }

    fun find(toteId: Long): Tote =
        toteRepository.findByIdOrNull(toteId) ?: throw IllegalArgumentException("Tote(ID: $toteId)를 찾을 수 없습니다.")
}
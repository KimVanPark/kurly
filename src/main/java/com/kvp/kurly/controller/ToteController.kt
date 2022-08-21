package com.kvp.kurly.controller

import com.kvp.kurly.dto.ToteCreateRequest
import com.kvp.kurly.dto.ToteResponse
import com.kvp.kurly.service.ToteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/totes")
class ToteController(
    private val toteService: ToteService,
) {

    @PostMapping
    fun create(@RequestBody request: ToteCreateRequest): ToteResponse =
        ToteResponse.from(toteService.create(request))

    @GetMapping("/{toteId}")
    fun getTote(@PathVariable toteId: Long): ToteResponse =
        ToteResponse.from(toteService.find(toteId))
}
package com.kvp.kurly.controller

import com.kvp.kurly.dto.PickingOrderCreateRequest
import com.kvp.kurly.dto.PickingOrderResponse
import com.kvp.kurly.service.PickingOrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/picking-orders")
class PickingOrderController(
    private val pickingOrderService: PickingOrderService
) {

    @PostMapping
    fun create(@RequestBody request: PickingOrderCreateRequest): PickingOrderResponse =
        PickingOrderResponse.from(pickingOrderService.create(request))
}
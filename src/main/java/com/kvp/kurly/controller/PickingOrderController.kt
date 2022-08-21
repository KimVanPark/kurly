package com.kvp.kurly.controller

import com.kvp.kurly.dto.PickingOrderCreateRequest
import com.kvp.kurly.dto.PickingOrderResponse
import com.kvp.kurly.dto.PickingRequest
import com.kvp.kurly.dto.PickingResponse
import com.kvp.kurly.service.PickingOrderService
import com.kvp.kurly.service.PickingService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/picking-orders")
class PickingOrderController(
    private val pickingOrderService: PickingOrderService,
    private val pickingService: PickingService,
) {

    @PostMapping
    fun create(@RequestBody request: PickingOrderCreateRequest): PickingOrderResponse =
        PickingOrderResponse.from(pickingOrderService.create(request))


    @PostMapping("/{pickingOrderId}/items/{pickingOrderItemId}/picking")
    fun picking(
        @PathVariable pickingOrderId: Long,
        @PathVariable pickingOrderItemId: Long,
        @RequestBody request: PickingRequest,
    ): PickingResponse =
        PickingResponse.from(pickingService.picking(pickingOrderItemId, request))
}
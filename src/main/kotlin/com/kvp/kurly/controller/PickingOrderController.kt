package com.kvp.kurly.controller

import com.kvp.kurly.dto.PickingOrderAssignRequest
import com.kvp.kurly.dto.PickingOrderCreateRequest
import com.kvp.kurly.dto.PickingOrderItemWithCountResponse
import com.kvp.kurly.dto.PickingOrderResponse
import com.kvp.kurly.dto.PickingOrderWithCountResponse
import com.kvp.kurly.dto.PickingRequest
import com.kvp.kurly.dto.PickingResponse
import com.kvp.kurly.service.PickingOrderService
import com.kvp.kurly.service.PickingService
import com.kvp.kurly.service.PointService
import com.kvp.kurly.service.WorkerService
import org.springframework.web.bind.annotation.GetMapping
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
    private val workerService: WorkerService,
) {

    @PostMapping
    fun create(@RequestBody request: PickingOrderCreateRequest): PickingOrderResponse =
        PickingOrderResponse.from(pickingOrderService.create(request))

    @GetMapping("/{pickingOrderId}")
    fun getPickingOrder(@PathVariable pickingOrderId: Long): PickingOrderWithCountResponse =
        pickingOrderService.getPickingOrderResponse(pickingOrderId)


    @PostMapping("/assignment")
    fun assign(@RequestBody request: PickingOrderAssignRequest): PickingOrderWithCountResponse {
        val worker = workerService.find(request.workerId)
        val assignedPickingOrder = pickingOrderService.assign(worker, request)

        return assignedPickingOrder
    }


    @PostMapping("/{pickingOrderId}/items/{pickingOrderItemId}/picking")
    fun picking(
        @PathVariable pickingOrderId: Long,
        @PathVariable pickingOrderItemId: Long,
        @RequestBody request: PickingRequest,
    ): PickingOrderItemWithCountResponse =
        PickingOrderItemWithCountResponse.from(pickingService.picking(pickingOrderId, pickingOrderItemId, request))
}

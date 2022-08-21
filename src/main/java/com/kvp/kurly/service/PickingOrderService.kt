package com.kvp.kurly.service

import com.kvp.kurly.domain.LocationRepository
import com.kvp.kurly.domain.PickingOrder
import com.kvp.kurly.domain.PickingOrderItem
import com.kvp.kurly.domain.PickingOrderItemRepository
import com.kvp.kurly.domain.PickingOrderRepository
import com.kvp.kurly.domain.SkuRepository
import com.kvp.kurly.dto.PickingOrderAssignRequest
import com.kvp.kurly.dto.PickingOrderCreateRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PickingOrderService(
    private val pickingOrderRepository: PickingOrderRepository,
    private val pickingOrderItemRepository: PickingOrderItemRepository,
    private val skuRepository: SkuRepository,
    private val locationRepository: LocationRepository,
) {

    @Transactional
    fun create(request: PickingOrderCreateRequest): PickingOrder {
        val pickingOrder = pickingOrderRepository.save(
            PickingOrder()
        )

        val items = request.items.map { item ->
            PickingOrderItem(
                pickingOrder = pickingOrder,
                location = locationRepository.findByCode(item.locationCode)
                    .orElseThrow { IllegalArgumentException("Location 코드(${item.locationCode})를 찾을 수 없습니다.") },
                sku = skuRepository.findByCode(item.skuCode)
                    .orElseThrow { IllegalArgumentException("Sku 코드(${item.skuCode})를 찾을 수 없습니다.") },
                count = item.count
            )
        }
        pickingOrderItemRepository.saveAll(items)

        return pickingOrder
    }

    fun getPickingOrder(pickingOrderId: Long): PickingOrder =
        pickingOrderRepository.findByIdOrNull(pickingOrderId)
            ?: throw IllegalArgumentException("Picking Order(ID: $pickingOrderId)를 찾을 수 없습니다.")

    @Transactional
    fun assign(request: PickingOrderAssignRequest): PickingOrder {
        val pickingOrder = pickingOrderRepository.findFirstByWorkerIdIsNullOrderById()
            ?: throw IllegalStateException("작업자가 할당되지 않은 요청서가 없습니다.")


        pickingOrder.assign(0) // FIXME Worker 찾아서 Id 넣기
        return pickingOrder
    }
}
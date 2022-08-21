package com.kvp.kurly.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
final class PickingOrderItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picking_order_item_id", nullable = false)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "picking_order_id")
    val pickingOrder: PickingOrder,

    @ManyToOne
    @JoinColumn(name = "location_id")
    val location: Location,

    @ManyToOne
    @JoinColumn(name = "sku_id")
    val sku: Sku,

    val count: Int,
) {

    init {
        pickingOrder.addPickingOrderItem(this)
    }
}
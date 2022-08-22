package com.kvp.kurly.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class PickingOrderItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picking_order_item_id", nullable = false)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picking_order_id")
    val pickingOrder: PickingOrder,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    val location: Location,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id")
    val sku: Sku,

    val count: Int,

    @OneToMany(mappedBy = "pickingOrderItem")
    val pickingHistories: MutableList<Picking> = mutableListOf()
) {

    init {
        pickingOrder.addPickingOrderItem(this)
    }

    fun addPicking(picking: Picking) {
        pickingHistories.add(picking)
    }
}
package com.kvp.kurly.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class PickingOrder(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picking_order_id", nullable = false)
    val id: Long = 0L,

//    val worker: Worker?,

    @OneToMany(mappedBy = "pickingOrder")
    val items: MutableList<PickingOrderItem> = mutableListOf()
) {

    fun addPickingOrderItem(item: PickingOrderItem) {
        items.add(item)
    }
}
package com.kvp.kurly.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Picking(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picking_id", nullable = false)
    val id: Long = 0L,

    val workerId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tote_id")
    val tote: Tote,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picking_order_item_id")
    val pickingOrderItem: PickingOrderItem,

    val count: Int,

    val pickingAt: LocalDateTime = LocalDateTime.now(),
) {

    init {
        pickingOrderItem.addPicking(this)
    }
}
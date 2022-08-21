package com.kvp.kurly.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Tote(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tote_id", nullable = false)
    val id: Long = 0L,

    @Column(name = "code")
    val code: String,
)
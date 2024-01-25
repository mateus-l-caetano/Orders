package com.mateus.orders.domain.model

import java.time.LocalDateTime

data class Order(
    val id: Int,
    val date: LocalDateTime,
)

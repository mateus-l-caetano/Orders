package com.mateus.orders.domain.model

import com.mateus.orders.data.local.entity.OrderEntity
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Order(
    val id: Int,
    val date: LocalDateTime?,
)

fun Order.toOrderEntity(): OrderEntity {
    return OrderEntity(
       id, date.toString()
    )
}
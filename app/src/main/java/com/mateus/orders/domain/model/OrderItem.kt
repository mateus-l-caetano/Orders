package com.mateus.orders.domain.model

import com.mateus.orders.data.local.entity.OrderItemEntity

data class OrderItem(
    val id: Int,
    val quantity: Int,
    val productId: Int
)

fun OrderItem.toOrderItemEntity() = OrderItemEntity(
    id, quantity, productId
)
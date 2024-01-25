package com.mateus.orders.domain.model

data class OrderItem(
    val id: Int,
    val quantity: Int,
    val productId: Int
)

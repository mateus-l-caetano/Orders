package com.mateus.orders.domain.model

import com.mateus.orders.data.local.entity.OrderToOrderItemEntity

data class OrderToOrderItem(
    val orderId: Int,
    val orderItemId: Int
)

fun OrderToOrderItem.toOrderToOrderItem() = OrderToOrderItemEntity(
    0, orderId, orderItemId
)

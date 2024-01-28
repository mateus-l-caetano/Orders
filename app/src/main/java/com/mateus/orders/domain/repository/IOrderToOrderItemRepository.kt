package com.mateus.orders.domain.repository

import com.mateus.orders.domain.model.OrderToOrderItem

interface IOrderToOrderItemRepository {
    suspend fun addOrderToOrderItem(orderToOrderItem: OrderToOrderItem)
}
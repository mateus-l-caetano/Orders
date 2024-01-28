package com.mateus.orders.domain.repository

import com.mateus.orders.domain.model.OrderItem
import kotlinx.coroutines.flow.Flow

interface IOrderItemRepository {
    suspend fun getOrderItemByOrderIdAndProductId(orderId: Int, productId: Int): Flow<OrderItem?>

    suspend fun addNewOrderItem(orderItem: OrderItem) : Long

    suspend fun updateOrderItem(orderItem: OrderItem)
}
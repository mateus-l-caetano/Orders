package com.mateus.orders.domain.repository

import com.mateus.orders.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {
    suspend fun addOrder(order: Order) : Long

    suspend fun getCurrentOrder() : Flow<Order?>
}
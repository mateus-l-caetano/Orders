package com.mateus.orders.domain.repository

import com.mateus.orders.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface IOrderRepository {
    suspend fun invoke(): Flow<Order>
}
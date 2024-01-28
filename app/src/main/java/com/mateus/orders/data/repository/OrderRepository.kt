package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.data.local.entity.toOder
import com.mateus.orders.domain.model.Order
import com.mateus.orders.domain.model.toOrderEntity
import com.mateus.orders.domain.repository.IOrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val database: Database
) : IOrderRepository {
    override suspend fun addOrder(order: Order) : Long {
        return database.orderDao()
            .addOrder(order.toOrderEntity())
    }

    override suspend fun getCurrentOrder(): Flow<Order?> {
        return database.orderDao()
            .getCurrentOrder().map {
                if(it != null)
                    it.toOder()
                else null
            }
    }
}
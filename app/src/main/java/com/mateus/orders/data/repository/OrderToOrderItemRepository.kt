package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.domain.model.OrderToOrderItem
import com.mateus.orders.domain.model.toOrderToOrderItem
import com.mateus.orders.domain.repository.IOrderToOrderItemRepository
import javax.inject.Inject

class OrderToOrderItemRepository @Inject constructor(
    private val database: Database
) : IOrderToOrderItemRepository {
    override suspend fun addOrderToOrderItem(orderToOrderItem: OrderToOrderItem) {
        database.orderToOrderItemDao().addOrderToOrderItem(orderToOrderItem.toOrderToOrderItem())
    }
}
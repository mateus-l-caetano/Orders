package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.data.local.entity.toOrderItem
import com.mateus.orders.domain.model.OrderItem
import com.mateus.orders.domain.model.toOrderItemEntity
import com.mateus.orders.domain.repository.IOrderItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderItemRepository @Inject constructor(
    private val database: Database
) : IOrderItemRepository {

//    override suspend fun getOrderItem(vararg orderItensIds: Int): Flow<OrderItem> {
//        return database.orderItemDao().getOrderItem().map { it.toOrderItem() }
//    }

    override suspend fun getOrderItemByOrderIdAndProductId(
        orderId: Int,
        productId: Int
    ): Flow<OrderItem?> {
        return database.orderItemDao()
            .getOrderItemByOrderIdAndProductId(orderId, productId).map {
                if(it != null)
                    it.toOrderItem()
                else null
            }
    }

    override suspend fun addNewOrderItem(orderItem: OrderItem): Long {
        return database.orderItemDao().addNewOrderItem(orderItem.toOrderItemEntity())
    }

    override suspend fun updateOrderItem(orderItem: OrderItem) {
        database.orderItemDao().updateOrderItem(orderItem.toOrderItemEntity())
    }

    override suspend fun removeOrderItem(orderItem: OrderItem) {
        database.orderItemDao().removeOrderItem(orderItem.toOrderItemEntity())
    }
}
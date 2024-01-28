package com.mateus.orders.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.mateus.orders.data.local.entity.OrderToOrderItemEntity

@Dao
interface OrderToOrderItemDao {
    @Insert
    fun addOrderToOrderItem(orderToOrderItemEntity: OrderToOrderItemEntity)
}
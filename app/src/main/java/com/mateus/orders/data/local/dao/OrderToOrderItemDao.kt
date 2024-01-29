package com.mateus.orders.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mateus.orders.data.local.entity.OrderToOrderItemEntity

@Dao
interface OrderToOrderItemDao {
    @Insert
    fun addOrderToOrderItem(orderToOrderItemEntity: OrderToOrderItemEntity)

    @Query("DELETE FROM order_to_order_item WHERE order_id = :orderId AND order_item_id = :orderItemId")
    fun removeOrderToOrderItem(orderId: Int, orderItemId: Int)
}
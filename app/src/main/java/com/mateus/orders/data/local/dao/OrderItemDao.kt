package com.mateus.orders.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mateus.orders.data.local.entity.OrderItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Query("SELECT * FROM order_item oi WHERE (SELECT order_item_id FROM order_to_order_item otoi WHERE otoi.order_id = :orderId AND otoi.order_item_id = oi.id) = oi.id AND oi.product_id = :productId")
    fun getOrderItemByOrderIdAndProductId(orderId: Int, productId: Int): Flow<OrderItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewOrderItem(orderItemEntity: OrderItemEntity) : Long

    @Update
    fun updateOrderItem(orderItemEntity: OrderItemEntity)

    @Delete
    fun removeOrderItem(orderItemEntity: OrderItemEntity)
}
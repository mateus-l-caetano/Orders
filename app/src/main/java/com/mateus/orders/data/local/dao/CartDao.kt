package com.mateus.orders.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mateus.orders.data.local.entity.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT p.id, p.name, p.description, p.price, oi.quantity FROM product p JOIN order_item oi ON oi.product_id = p.id JOIN order_to_order_item otoi ON otoi.order_id = :orderId GROUP BY p.id")
    fun getCartItems(orderId: Int) : Flow<List<CartItem>>
}
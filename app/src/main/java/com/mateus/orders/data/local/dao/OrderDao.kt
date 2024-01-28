package com.mateus.orders.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mateus.orders.data.local.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM 'order' ORDER BY date IS NULL OR date=''")
    fun getCurrentOrder() : Flow<OrderEntity>

    @Insert
    fun addOrder(order: OrderEntity) : Long
}
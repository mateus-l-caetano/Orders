package com.mateus.orders.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mateus.orders.data.local.entity.OrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM 'order' WHERE date IS NULL OR date = 'null' OR date = ''")
    fun getCurrentOrder() : Flow<OrderEntity>

    @Insert
    fun addOrder(order: OrderEntity) : Long

    @Update
    fun updateOrder(order: OrderEntity)
}
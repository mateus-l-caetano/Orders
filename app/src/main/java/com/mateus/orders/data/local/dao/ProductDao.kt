package com.mateus.orders.data.local.dao

import androidx.room.Insert
import androidx.room.Query
import com.mateus.orders.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductDao {
    @Query("SELECT * FROM product")
    suspend fun getProducts(): Flow<List<ProductEntity>>

    @Insert
    suspend fun saveProduct(vararg productEntities: ProductEntity)
}


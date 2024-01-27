package com.mateus.orders.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mateus.orders.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE category_id IN (:categoryIds)")
    fun getProductsByCategory(categoryIds: IntArray): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProducts(vararg products: ProductEntity)
}


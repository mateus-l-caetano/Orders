package com.mateus.orders.domain.repository

import com.mateus.orders.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getProducts(): Flow<List<Product>>

    suspend fun getProductsByCategory(categoryIds: List<Int>): Flow<List<Product>>
}
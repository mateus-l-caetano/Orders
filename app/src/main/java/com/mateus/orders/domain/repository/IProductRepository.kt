package com.mateus.orders.domain.repository

import com.mateus.orders.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getProducts(categoryIds: List<Int>): Flow<List<Product>>
}
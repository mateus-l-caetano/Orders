package com.mateus.orders.domain.repository

import com.mateus.orders.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface ICategoryRepository {
    suspend fun getCategories(): Flow<List<Category>>
}
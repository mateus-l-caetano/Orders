package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.data.remote.IFakeRemoteService
import com.mateus.orders.domain.model.Category
import com.mateus.orders.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val database: Database,
    private val orderService: IFakeRemoteService
) : ICategoryRepository {
    override suspend fun getCaterogiesRepository(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }
}
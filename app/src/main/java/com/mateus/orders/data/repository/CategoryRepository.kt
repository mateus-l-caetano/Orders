package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.data.local.entity.toCategory
import com.mateus.orders.data.remote.IFakeRemoteService
import com.mateus.orders.domain.model.Category
import com.mateus.orders.domain.repository.ICategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val database: Database,
    private val orderService: IFakeRemoteService
) : ICategoryRepository {
    override suspend fun getCategories(): Flow<List<Category>> {
        return database.categoryDao()
            .getCategories().map { categoriesList ->
                if(categoriesList.isEmpty()) {
                    getCategoriesFromApi()
                }
                categoriesList.map { categoryEntity ->
                    categoryEntity.toCategory()
                }
            }
    }

    private suspend fun getCategoriesFromApi() {
        val categories = orderService.getCategorries()
        database.categoryDao().saveCategories(categories = categories.toTypedArray())
    }
}
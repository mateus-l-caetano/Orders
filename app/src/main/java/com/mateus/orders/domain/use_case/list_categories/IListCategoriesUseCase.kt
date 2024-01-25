package com.mateus.orders.domain.use_case.list_categories

import com.mateus.orders.domain.model.Category
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IListCategoriesUseCase {
    operator fun invoke(): Flow<Resource<List<Category>>>
}
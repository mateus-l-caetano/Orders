package com.mateus.orders.domain.use_case.list_products

import com.mateus.orders.domain.model.Product
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IListProductsUseCase {
    operator fun invoke(categoryIds: List<Int>) : Flow<Resource<List<Product>>>
}
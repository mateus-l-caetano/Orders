package com.mateus.orders.domain.use_case.get_product_details

import com.mateus.orders.domain.model.Product
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IGetProductDetailsUseCase {
    operator fun invoke(id: Int): Flow<Resource<Product>>
}
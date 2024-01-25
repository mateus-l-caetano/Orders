package com.mateus.orders.domain.use_case.list_products

import com.mateus.orders.domain.model.Product
import com.mateus.orders.domain.repository.IProductRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListProductsUseCase @Inject constructor(
    private val repository: IProductRepository
) : IListProductsUseCase {
    override fun invoke(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Success(listOf()))
    }

}
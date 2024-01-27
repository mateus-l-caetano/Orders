package com.mateus.orders.domain.use_case.get_product_details

import com.mateus.orders.domain.model.Product
import com.mateus.orders.domain.repository.IProductRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: IProductRepository
) : IGetProductDetailsUseCase {
    override fun invoke(id: Int): Flow<Resource<Product>> = flow {
        emit(Resource.Loading())
        try {
            repository.getProductById(id).collect { product ->
                emit(Resource.Success(product))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Erro ao Carregar produto"))
        }
    }

}
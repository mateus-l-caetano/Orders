package com.mateus.orders.domain.use_case.list_products

import android.util.Log
import com.mateus.orders.domain.model.Product
import com.mateus.orders.domain.repository.IProductRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListProductsUseCase @Inject constructor(
    private val repository: IProductRepository
) : IListProductsUseCase {
    override fun invoke(categoryIds: List<Int>): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        try {
            if(categoryIds.isEmpty()) {
                repository.getProducts().collect { productsList ->
                    emit(Resource.Success(productsList))
                }
            } else {
                repository.getProductsByCategory(categoryIds).collect {productsList ->
                    emit(Resource.Success(productsList))
                }
            }
        } catch (e : Exception){
            emit(Resource.Error(
                message = e.message ?: "Erro ao buscar produtos"
            ))
        }
    }

}
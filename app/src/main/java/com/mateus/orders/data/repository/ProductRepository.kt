package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.data.local.entity.toProduct
import com.mateus.orders.data.remote.IFakeRemoteService
import com.mateus.orders.domain.model.Product
import com.mateus.orders.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val database: Database,
    private val orderService: IFakeRemoteService
    ) : IProductRepository {
    override suspend fun getProducts(): Flow<List<Product>> {
        return database.productDao()
            .getProducts().map { productsListFromRoom ->
                if(productsListFromRoom.isEmpty()){
                    getFromApi()
                }

                productsListFromRoom.map { productEntity ->
                    productEntity.toProduct()
                }
            }
    }

    override suspend fun getProductsByCategory(categoryIds: List<Int>): Flow<List<Product>> {
        return database.productDao()
            .getProductsByCategory(categoryIds.toIntArray()).map { productsList ->
                productsList.map { productEntity -> productEntity.toProduct()
            }
        }
    }

    private suspend fun getFromApi() {
        val products = orderService.getProducts()
        database.productDao().saveProducts(products = products.toTypedArray())
    }
}
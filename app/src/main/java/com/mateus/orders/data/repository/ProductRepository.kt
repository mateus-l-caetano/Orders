package com.mateus.orders.data.repository

import android.util.Log
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
    override suspend fun getProducts(categoryIds: List<Int>): Flow<List<Product>> {
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

    private suspend fun getFromApi() {
        val products = orderService.getProducts()
        Log.d("ProductRepository products from API size", products.size.toString())
//        products.forEach {
//            Log.d("saving product", it.toString())
//            database.productDao().saveProducts(
//                it
//            )
//        }
        database.productDao().saveProducts(products = products.toTypedArray())
    }
}
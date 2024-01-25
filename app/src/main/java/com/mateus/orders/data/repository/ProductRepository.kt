package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.data.remote.IFakeRemoteService
import com.mateus.orders.domain.model.Product
import com.mateus.orders.domain.repository.IProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val database: Database,
    private val orderService: IFakeRemoteService
    ) : IProductRepository {
    override suspend fun getProducts(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }
}
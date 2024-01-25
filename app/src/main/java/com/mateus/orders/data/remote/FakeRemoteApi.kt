package com.mateus.orders.data.remote

import com.mateus.orders.data.local.entity.CategoryEntity
import com.mateus.orders.data.local.entity.OrderEntity
import com.mateus.orders.data.local.entity.ProductEntity
import kotlinx.coroutines.delay
import java.math.BigDecimal

class FakeRemoteApi : IFakeRemoteService {
    override suspend fun getProducts(): List<ProductEntity> {
        val productEntities: MutableList<ProductEntity> = mutableListOf()

        for (i in 1..100){
            productEntities.add(ProductEntity(i, "product $i", BigDecimal(i*1.1), i%5))
        }

        delay(2000)
        return productEntities.toList()
    }

    override suspend fun getCategorries(): List<CategoryEntity> {
        val categories: MutableList<CategoryEntity> = mutableListOf()

        for (i in 1..5){
            categories.add(CategoryEntity(i, "category $i"))
        }

        delay(2000)
        return categories.toList()
    }

    override suspend fun makeOrder(orderEntity: OrderEntity): Boolean {
        delay(2000)
        return true
    }
}
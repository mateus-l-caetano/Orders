package com.mateus.orders.data.remote

import android.util.Log
import com.mateus.orders.data.local.entity.CategoryEntity
import com.mateus.orders.data.local.entity.OrderEntity
import com.mateus.orders.data.local.entity.ProductEntity
import kotlinx.coroutines.delay
import java.math.BigDecimal
import javax.inject.Inject

class FakeRemoteApi @Inject constructor() : IFakeRemoteService {
    override suspend fun getProducts(): List<ProductEntity> {
        val productEntities: MutableList<ProductEntity> = mutableListOf()

        for (i in 1..100){
            productEntities.add(
                ProductEntity(
                    id = i,
                    name = "product $i",
                    description = "The product $i is a very nice product",
                    price = BigDecimal(i).toString(),
                    categoryId = i % 5
                )
            )
        }

        delay(2000)

        Log.d("FakeRemoteApi products from api size", productEntities.size.toString())
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
package com.mateus.orders.data.remote

import com.mateus.orders.data.local.entity.CategoryEntity
import com.mateus.orders.data.local.entity.OrderEntity
import com.mateus.orders.data.local.entity.ProductEntity

interface IFakeRemoteService {
    suspend fun getProducts(): List<ProductEntity>

    suspend fun getCategorries(): List<CategoryEntity>

    suspend fun makeOrder(orderEntity: OrderEntity): Boolean
}
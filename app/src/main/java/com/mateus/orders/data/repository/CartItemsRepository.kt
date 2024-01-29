package com.mateus.orders.data.repository

import com.mateus.orders.data.local.Database
import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.domain.repository.ICartItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartItemsRepository @Inject constructor(
    private val database: Database
) : ICartItemsRepository {
    override suspend fun getCartItems(orderId: Int): Flow<List<CartItem>> {
        return database.cartDao().getCartItems(orderId)
    }
}
package com.mateus.orders.domain.repository

import com.mateus.orders.data.local.entity.CartItem
import kotlinx.coroutines.flow.Flow

interface ICartItemsRepository {
    suspend fun getCartItems(orderId: Int) : Flow<List<CartItem>>
}
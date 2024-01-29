package com.mateus.orders.domain.use_case.see_summary

import android.util.Log
import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.domain.repository.ICartItemsRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeeSummary @Inject constructor(
    private val repository: ICartItemsRepository,
) : ISeeSummary {
    override fun invoke(orderId: Int): Flow<Resource<List<CartItem>>> = flow{
        emit(Resource.Loading())
        try {
            repository.getCartItems(orderId).collect { cartItemsList ->
                Log.d("aaa", "cartItemListSize: ${cartItemsList.size}")
                cartItemsList.forEach { cartItem ->
                    Log.d("aaa", "item: ${cartItem.name}")
                }
                emit(Resource.Success(cartItemsList))
            }
        } catch (e : Exception) {
            emit(Resource.Error(
                e.message ?: "Erro ao buscar produtos"
            ))
        }
    }
}
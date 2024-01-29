package com.mateus.orders.presentation.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.domain.use_case.add_order_item.IAddOrderItemUseCase
import com.mateus.orders.domain.use_case.see_summary.ISeeSummary
import com.mateus.orders.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val seeSummary: ISeeSummary,
    private val addOrderItemUseCase: IAddOrderItemUseCase
) : ViewModel() {
    private val cartItems: MutableStateFlow<Resource<List<CartItem>>> = MutableStateFlow(Resource.Loading())
    private val addOrderItemState: MutableStateFlow<Resource<Any>> = MutableStateFlow(Resource.Loading())
    var orderId: Int = 0

    fun addProduct(productId: Int) {
        addOrderItemState.value = Resource.Loading()
        Log.d("aaa", "viewmodel add order: $orderId product : $productId")
        viewModelScope.launch(Dispatchers.IO) {
            addOrderItemUseCase(
                orderId, productId
            ).collect { success ->
                addOrderItemState.value = success
            }
        }
    }

    fun removeProduct(productId: Int) {
//        removeOrderItemUseCase(orderId, productId)
    }

    fun getAddOrderItemState() : StateFlow<Resource<Any>> = addOrderItemState.asStateFlow()

    fun getCartItems() : StateFlow<Resource<List<CartItem>>> = cartItems.asStateFlow()
    suspend fun loadCartItems() {
        Log.d("aaa", "loading cart")
        Log.d("aaa", "orderId: $orderId")
        seeSummary(orderId).collect { cartItemsListResource ->
            cartItems.value = cartItemsListResource
        }
    }
}
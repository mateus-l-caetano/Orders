package com.mateus.orders.presentation.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateus.orders.domain.model.Product
import com.mateus.orders.domain.use_case.add_order_item.IAddOrderItemUseCase
import com.mateus.orders.domain.use_case.get_product_details.IGetProductDetailsUseCase
import com.mateus.orders.domain.use_case.make_order.IMakeOrderUseCase
import com.mateus.orders.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: IGetProductDetailsUseCase,
    private val addOrderItemUseCase: IAddOrderItemUseCase,
    private val makeOrderUseCase: IMakeOrderUseCase
) : ViewModel() {
    private val product: MutableStateFlow<Resource<Product>> =
        MutableStateFlow(Resource.Loading())
    private val orderIdState: MutableStateFlow<Resource<Int>> = MutableStateFlow(Resource.Loading())
    private val addOrderItemState: MutableStateFlow<Resource<Any>> = MutableStateFlow(Resource.Loading())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadOrderId()
        }
    }

    fun getProduct(id: Int): StateFlow<Resource<Product>> {
        viewModelScope.launch(Dispatchers.IO) {
            getProductDetailsUseCase(id).collect { productResource ->
                product.value = productResource
            }
        }

        return product.asStateFlow()
    }

    private suspend fun loadOrderId() {
        makeOrderUseCase().collect { orderIdResource ->
            orderIdState.value = orderIdResource
        }
    }

    fun addOrderItem(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            orderIdState.value.data?.let { orderId ->
                addOrderItemUseCase(
                    orderId, productId
                ).collect { success ->
                    addOrderItemState.value = success
                }
            }
        }
    }
}
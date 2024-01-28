package com.mateus.orders.presentation.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateus.orders.domain.model.Product
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
    private val makeOrderUseCase: IMakeOrderUseCase
) : ViewModel() {
    private val product: MutableStateFlow<Resource<Product>> =
        MutableStateFlow(Resource.Loading())

    fun getProduct(id: Int): StateFlow<Resource<Product>> {
        viewModelScope.launch(Dispatchers.IO) {
            getProductDetailsUseCase(id).collect { productResource ->
                product.value = productResource
            }
        }

        return product.asStateFlow()
    }
}
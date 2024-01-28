package com.mateus.orders.domain.use_case.add_order_item

import com.mateus.orders.domain.model.OrderItem
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IAddOrderItemUseCase {
    operator fun invoke(orderId: Int, productId: Int) : Flow<Resource<Any>>
}
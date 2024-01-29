package com.mateus.orders.domain.use_case.remove_order_item

import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IRemoveOrderItemUseCase {
    operator fun invoke(orderId: Int, productId: Int) : Flow<Resource<Any>>
}
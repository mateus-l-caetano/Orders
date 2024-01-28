package com.mateus.orders.domain.use_case.make_order

import com.mateus.orders.domain.model.Order
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IMakeOrderUseCase {
    operator fun invoke() : Flow<Resource<Int>>
}
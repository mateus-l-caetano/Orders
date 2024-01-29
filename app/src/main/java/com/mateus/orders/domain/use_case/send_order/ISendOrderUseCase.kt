package com.mateus.orders.domain.use_case.send_order

import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ISendOrderUseCase {
    operator fun invoke() : Flow<Resource<Any>>
}
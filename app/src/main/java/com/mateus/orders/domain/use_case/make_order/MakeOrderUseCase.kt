package com.mateus.orders.domain.use_case.make_order

import com.mateus.orders.domain.repository.IOrderRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MakeOrderUseCase @Inject constructor(
    private val repository: IOrderRepository
) : IMakeOrderUseCase {
    override fun invoke(): Flow<Resource<Boolean>> = flow { emit(Resource.Success(true)) }
}
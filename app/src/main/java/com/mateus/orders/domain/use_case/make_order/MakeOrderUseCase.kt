package com.mateus.orders.domain.use_case.make_order

import android.util.Log
import com.mateus.orders.domain.model.Order
import com.mateus.orders.domain.repository.IOrderRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MakeOrderUseCase @Inject constructor(
    private val repository: IOrderRepository
) : IMakeOrderUseCase {
    override fun invoke(): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())
        try {
            val currentOrder = repository.getCurrentOrder().firstOrNull()

            if (currentOrder == null) {
                val id = repository.addOrder(Order(0, null)).toInt()
                if(id >= 0)
                    emit(Resource.Success(id))
            } else {
                emit(Resource.Success(currentOrder.id))
            }

        } catch (e: Exception) {
            emit(Resource.Error(
                e.message ?: "Erro ao adicionar o pedido"
            ))
        }
    }
}
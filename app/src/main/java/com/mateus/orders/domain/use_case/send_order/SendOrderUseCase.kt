package com.mateus.orders.domain.use_case.send_order

import com.mateus.orders.domain.model.Order
import com.mateus.orders.domain.repository.IOrderRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject

class SendOrderUseCase @Inject constructor(
    private val repository: IOrderRepository
) : ISendOrderUseCase {
    override fun invoke(): Flow<Resource<Any>> = flow {
        emit(Resource.Loading())
        try {
            val currentOrder = repository.getCurrentOrder().firstOrNull()

            if(currentOrder != null){
                repository.sendOrder(Order(
                    currentOrder.id,
                    LocalDateTime.now()
                ))
                emit(Resource.Success(Any()))
            } else {
                emit(Resource.Error("Não foi possível enviar"))
            }
        } catch (e : Exception) {
            emit(Resource.Error(e.message ?: "Erro ao enviar pedido"))
        }
    }

}
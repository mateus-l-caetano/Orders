package com.mateus.orders.domain.use_case.add_order_item

import android.util.Log
import com.mateus.orders.domain.model.OrderItem
import com.mateus.orders.domain.model.OrderToOrderItem
import com.mateus.orders.domain.repository.IOrderItemRepository
import com.mateus.orders.domain.repository.IOrderToOrderItemRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddOrderItemUseCase @Inject constructor(
    private val orderItemRepository: IOrderItemRepository,
    private val orderToOrderItemRepository: IOrderToOrderItemRepository
) : IAddOrderItemUseCase {
    override fun invoke(orderId: Int, productId: Int): Flow<Resource<Any>> = flow {
        emit(Resource.Loading())
        try {
            val orderItem = orderItemRepository.getOrderItemByOrderIdAndProductId(orderId, productId).firstOrNull()
            Log.d("aaa", "${orderItem.toString()} orderid: $orderId prodid: $productId")

            if(orderItem == null) {
                val orderItemId = orderItemRepository.addNewOrderItem(
                    OrderItem(0, 1, productId)
                ).toInt()

                orderToOrderItemRepository.addOrderToOrderItem(
                    OrderToOrderItem(orderId, orderItemId)
                )
                emit(Resource.Success(Any()))
            } else {
                orderItemRepository.updateOrderItem(
                    OrderItem(
                        orderItem.id, orderItem.quantity + 1, orderItem.productId
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Error(
                e.message ?: "Erro ao adicionar item ao pedido"
            ))
        }
    }
}
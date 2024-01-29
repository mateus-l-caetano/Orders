package com.mateus.orders.domain.use_case.remove_order_item

import com.mateus.orders.domain.model.OrderItem
import com.mateus.orders.domain.repository.IOrderItemRepository
import com.mateus.orders.domain.repository.IOrderToOrderItemRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveOrderItemUseCase @Inject constructor(
    private val orderItemRepository: IOrderItemRepository,
    private val orderToOrderItemRepository: IOrderToOrderItemRepository
) : IRemoveOrderItemUseCase {
    override fun invoke(orderId: Int, productId: Int): Flow<Resource<Any>> = flow { 
        emit(Resource.Loading())
        
        try {
            val orderItem: OrderItem? = orderItemRepository
                .getOrderItemByOrderIdAndProductId(orderId, productId).first()

            orderItem?.quantity?.let { quantity -> 
                if (quantity > 1) {
                    orderItemRepository.updateOrderItem(
                        OrderItem(
                            orderItem.id, orderItem.quantity - 1, orderItem.productId
                        )
                    )
                } else {
                    orderItemRepository.removeOrderItem(orderItem)
                    orderToOrderItemRepository.removeOrderToOrderItem(orderId, productId)
                }

                emit(Resource.Success(Any()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(
                e.message ?: "Erro ao remover item"
            ))
        }
    }
}
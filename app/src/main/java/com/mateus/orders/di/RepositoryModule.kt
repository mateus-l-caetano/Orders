package com.mateus.orders.di

import com.mateus.orders.data.repository.CategoryRepository
import com.mateus.orders.data.repository.OrderItemRepository
import com.mateus.orders.data.repository.OrderRepository
import com.mateus.orders.data.repository.OrderToOrderItemRepository
import com.mateus.orders.data.repository.ProductRepository
import com.mateus.orders.domain.repository.ICategoryRepository
import com.mateus.orders.domain.repository.IOrderItemRepository
import com.mateus.orders.domain.repository.IOrderRepository
import com.mateus.orders.domain.repository.IOrderToOrderItemRepository
import com.mateus.orders.domain.repository.IProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindProductRepository(
        productRepository: ProductRepository
    ) : IProductRepository

    @Binds
    fun bindCategoryRepository(
        categoryRepository: CategoryRepository
    ) : ICategoryRepository

    @Binds
    fun bindOrderRepository(
        orderRepository: OrderRepository
    ) : IOrderRepository

    @Binds
    fun bindOrderItemRepository(
        orderItemRepository: OrderItemRepository
    ) : IOrderItemRepository

    @Binds
    fun bindOrderToOrderItemRepository(
        orderToOrderItemRepository: OrderToOrderItemRepository
    ) : IOrderToOrderItemRepository

}
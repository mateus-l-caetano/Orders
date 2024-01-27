package com.mateus.orders.di

import com.mateus.orders.data.repository.CategoryRepository
import com.mateus.orders.data.repository.ProductRepository
import com.mateus.orders.domain.repository.ICategoryRepository
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

    @Binds fun bindCategoryRepository(
        categoryRepository: CategoryRepository
    ) : ICategoryRepository

}
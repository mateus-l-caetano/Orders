package com.mateus.orders.di

import com.mateus.orders.domain.use_case.get_product_details.GetProductDetailsUseCase
import com.mateus.orders.domain.use_case.get_product_details.IGetProductDetailsUseCase
import com.mateus.orders.domain.use_case.list_categories.IListCategoriesUseCase
import com.mateus.orders.domain.use_case.list_categories.ListCategoriesUseCase
import com.mateus.orders.domain.use_case.list_products.IListProductsUseCase
import com.mateus.orders.domain.use_case.list_products.ListProductsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindListCategoriesUseCase(
        listCategoriesUseCase: ListCategoriesUseCase
    ) : IListCategoriesUseCase

    @Binds
    fun bindListProductsUseCase(
        listProductsUseCase: ListProductsUseCase
    ) : IListProductsUseCase

    @Binds
    fun bindGetProductDetailsUseCase(
        getProductDetailsUseCase: GetProductDetailsUseCase
    ) : IGetProductDetailsUseCase

}
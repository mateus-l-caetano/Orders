package com.mateus.orders.di

import com.mateus.orders.domain.use_case.add_order_item.AddOrderItemUseCase
import com.mateus.orders.domain.use_case.add_order_item.IAddOrderItemUseCase
import com.mateus.orders.domain.use_case.get_product_details.GetProductDetailsUseCase
import com.mateus.orders.domain.use_case.get_product_details.IGetProductDetailsUseCase
import com.mateus.orders.domain.use_case.list_categories.IListCategoriesUseCase
import com.mateus.orders.domain.use_case.list_categories.ListCategoriesUseCase
import com.mateus.orders.domain.use_case.list_products.IListProductsUseCase
import com.mateus.orders.domain.use_case.list_products.ListProductsUseCase
import com.mateus.orders.domain.use_case.make_order.IMakeOrderUseCase
import com.mateus.orders.domain.use_case.make_order.MakeOrderUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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

    @Binds
    fun bindMakeOrderUseCase(
        makeOrderUseCase: MakeOrderUseCase
    ) : IMakeOrderUseCase

    @Binds
    fun bindAddOrderItemUseCase(
        addOrderItemUseCase: AddOrderItemUseCase
    ) : IAddOrderItemUseCase

}
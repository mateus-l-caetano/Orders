package com.mateus.orders.domain.use_case.see_summary

import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ISeeSummary {
    operator fun invoke(orderId: Int) : Flow<Resource<List<CartItem>>>
}
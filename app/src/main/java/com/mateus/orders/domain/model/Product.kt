package com.mateus.orders.domain.model

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val price: BigDecimal,
    val categoryId: Int
)

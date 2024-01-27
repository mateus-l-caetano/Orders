package com.mateus.orders.domain.model

import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val categoryId: Int
)

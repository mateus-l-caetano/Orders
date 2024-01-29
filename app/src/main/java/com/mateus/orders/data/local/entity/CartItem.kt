package com.mateus.orders.data.local.entity

data class CartItem (
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val quantity: Int
)
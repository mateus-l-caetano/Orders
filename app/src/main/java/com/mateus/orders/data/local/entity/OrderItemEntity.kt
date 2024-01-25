package com.mateus.orders.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_item")
data class OrderItemEntity(
    @PrimaryKey val id: Int,
    val quantity: Int,
    @ColumnInfo(name = "product_id") val productId: Int
)

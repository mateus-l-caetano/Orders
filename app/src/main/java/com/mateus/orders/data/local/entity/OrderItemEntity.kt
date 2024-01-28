package com.mateus.orders.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateus.orders.domain.model.OrderItem

@Entity(tableName = "order_item")
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quantity: Int,
    @ColumnInfo(name = "product_id") val productId: Int
)

fun OrderItemEntity.toOrderItem() = OrderItem(
    id, quantity, productId
)

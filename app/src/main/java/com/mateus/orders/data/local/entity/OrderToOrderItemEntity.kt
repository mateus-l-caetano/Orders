package com.mateus.orders.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_to_order_item")
data class OrderToOrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "order_id")
    val orderId: Int,
    @ColumnInfo(name = "order_item_id")
    val orderItemId: Int
)

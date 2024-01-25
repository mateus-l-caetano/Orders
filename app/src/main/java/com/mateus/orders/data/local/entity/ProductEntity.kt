package com.mateus.orders.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: BigDecimal,
    @ColumnInfo(name = "category_id") val categoryId: Int
)

package com.mateus.orders.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateus.orders.domain.model.Product
import java.math.BigDecimal

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "category_id") val categoryId: Int
)

fun ProductEntity.toProduct() : Product {
    return Product(
        id,
        name,
        description,
        BigDecimal(price),
        categoryId
    )
}
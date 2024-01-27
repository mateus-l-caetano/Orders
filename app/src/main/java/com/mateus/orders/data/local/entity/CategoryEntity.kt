package com.mateus.orders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateus.orders.domain.model.Category

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String
)

fun CategoryEntity.toCategory(): Category {
    return Category(
        id, name
    )
}

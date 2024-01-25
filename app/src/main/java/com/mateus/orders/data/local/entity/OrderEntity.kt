package com.mateus.orders.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "order")
data class OrderEntity(
    @PrimaryKey val id: Int,
    val date: LocalDateTime,
)

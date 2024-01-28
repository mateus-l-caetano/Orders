package com.mateus.orders.data.local.entity

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateus.orders.domain.model.Order
import java.time.LocalDateTime

@Entity(tableName = "order")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "date") val date: String?,
)

fun OrderEntity.toOder(): Order {
    var localDateTime: LocalDateTime? = null
    try {
        localDateTime = LocalDateTime.parse(date)
    } finally {
        return Order(id, localDateTime)
    }
}

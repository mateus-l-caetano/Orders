package com.mateus.orders.data.local

import androidx.room.Database
import com.mateus.orders.data.local.dao.CategoryDao
import com.mateus.orders.data.local.dao.OrderDao
import com.mateus.orders.data.local.dao.OrderItemDao
import com.mateus.orders.data.local.dao.OrderToOrderItemDao
import com.mateus.orders.data.local.dao.ProductDao
import com.mateus.orders.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class Database {
    abstract fun productDao(): ProductDao

    abstract fun categoryDao(): CategoryDao

    abstract fun orderDao(): OrderDao

    abstract fun orderItemDao(): OrderItemDao

    abstract fun orderToOrderItemDao(): OrderToOrderItemDao
}
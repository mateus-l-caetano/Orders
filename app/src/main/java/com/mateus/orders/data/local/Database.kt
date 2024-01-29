package com.mateus.orders.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mateus.orders.data.local.dao.CartDao
import com.mateus.orders.data.local.dao.CategoryDao
import com.mateus.orders.data.local.dao.OrderDao
import com.mateus.orders.data.local.dao.OrderItemDao
import com.mateus.orders.data.local.dao.OrderToOrderItemDao
import com.mateus.orders.data.local.dao.ProductDao
import com.mateus.orders.data.local.entity.CategoryEntity
import com.mateus.orders.data.local.entity.OrderEntity
import com.mateus.orders.data.local.entity.OrderItemEntity
import com.mateus.orders.data.local.entity.OrderToOrderItemEntity
import com.mateus.orders.data.local.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        OrderToOrderItemEntity::class
    ],
    version = 1)
abstract class Database: RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun categoryDao(): CategoryDao

    abstract fun orderDao(): OrderDao

    abstract fun orderItemDao(): OrderItemDao

    abstract fun orderToOrderItemDao(): OrderToOrderItemDao

    abstract fun cartDao(): CartDao
}
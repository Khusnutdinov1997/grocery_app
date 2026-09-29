package com.example.groceryapp.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.groceryapp.data.local.CartEntity
import com.example.groceryapp.data.local.dao.CartDao

@Database(
    entities = [
        CartEntity::class
    ], version = 1
)
abstract class Database : RoomDatabase() {
    abstract val cartDao: CartDao
}
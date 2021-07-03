package com.example.stock.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stock.api.Stock

@Database(entities = [Stock::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao
}
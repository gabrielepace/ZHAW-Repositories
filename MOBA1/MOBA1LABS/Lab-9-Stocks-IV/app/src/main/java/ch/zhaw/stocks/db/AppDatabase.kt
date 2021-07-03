package ch.zhaw.stocks.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ch.zhaw.stocks.api.Stock

@Database(entities = arrayOf(Stock::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao
}
package com.example.stock.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stock.api.Stock

@Dao
interface StockDao {
    @Query("SELECT * FROM stock")
    fun getAll(): List<Stock>

    @Query("SELECT * FROM stock WHERE uid IN (:uids)")
    fun loadAllByIds(uids: IntArray): List<Stock>

//    @Query("SELECT * FROM stock WHERE stock_name IN (:stockNamesArr)")
//    fun loadAllByFirstName(stockNamesArr: List<String>): List<Stock>
//
//    @Query("SELECT * FROM stock WHERE stock_name LIKE :stockName LIMIT 1")
//    fun findByName(stockName: String): Stock

    @Insert
    fun insert(vararg stock: Stock)

    @Delete
    fun delete(stock: Stock)
}
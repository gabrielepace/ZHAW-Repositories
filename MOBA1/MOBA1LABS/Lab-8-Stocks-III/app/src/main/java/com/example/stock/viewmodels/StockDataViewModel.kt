package com.example.stock.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.stock.api.Stock
import com.example.stock.api.StockEndpoint
import com.example.stock.db.AppDatabase

class StockDataViewModel : ViewModel() {
    var stockItems = MutableLiveData<MutableList<Stock>>(mutableListOf())
    private lateinit var db : AppDatabase

    fun loadStockFromApi(context: Context, items : MutableList<Stock>) {
        StockEndpoint().getStockData(context, items)
//        stockItems.postValue(stockItems.value)
    }

    fun initDB(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    private fun getStock() {
        val dbItems = db.stockDao().getAll().toMutableList()

        stockItems.value!!.clear()
        stockItems.value!!.addAll(dbItems)
        stockItems.postValue(stockItems.value!!)
    }

    fun addStock(stocks: List<Stock>) {
        stocks.forEach {
            db.stockDao().insert(it)
        }
        getStock()
    }

}
package ch.zhaw.stocks.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import ch.zhaw.stocks.api.Stock
import ch.zhaw.stocks.api.StockDetails
import ch.zhaw.stocks.api.StockEndpoint
import ch.zhaw.stocks.db.AppDatabase

class StockDataViewModel : ViewModel() {
    var stockItems = MutableLiveData<MutableList<Stock>>(mutableListOf())
    var stockDetails = MutableLiveData<StockDetails>()
    private lateinit var db : AppDatabase

    init {
//        stockItems = MutableLiveData<MutableList<Stock>>()
//        stockItems.value = mutableListOf<Stock>()
    }

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

    suspend fun getStock() {
        val dbItems = db.stockDao().getAll().toMutableList()

        stockItems.value!!.clear()
        stockItems.value!!.addAll(dbItems)
        stockItems.postValue(stockItems.value!!)
    }

    suspend fun addStock(stocks: List<Stock>) {
        stocks.forEach {
            db.stockDao().insert(it)
        }
        getStock()
    }

}
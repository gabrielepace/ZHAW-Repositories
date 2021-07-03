package com.example.stock

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.adapters.StockAdapter
import com.example.stock.api.Stock
import com.example.stock.viewmodels.StockDataViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var items = mutableListOf<Stock>()
    private val stockModel: StockDataViewModel by viewModels()

//        private var stockItems = stockModel.stockItems
    private val stockAdapter = StockAdapter(items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        initRecyclerView(stockRecyclerView, stockAdapter)
        stockModel.initDB(this)

        stockModel.stockItems.observe(this, { stocks ->
            items.clear()
            stocks.forEach {
                items.add(it)
            }
            stockAdapter.notifyDataSetChanged()
        })

        val newItems = mutableListOf<Stock>()
        stockModel.loadStockFromApi(this, newItems )

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            lifecycleScope.launch(Dispatchers.Default) {
                stockModel.addStock(newItems) //stockModel.stockItems.value!!)
            }
        }

    }

    private fun initRecyclerView(recyclerView: RecyclerView, adapter: StockAdapter) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
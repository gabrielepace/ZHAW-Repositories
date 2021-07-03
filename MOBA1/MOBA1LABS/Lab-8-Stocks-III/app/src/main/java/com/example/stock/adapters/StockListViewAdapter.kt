package com.example.stock.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.stock.R
import com.example.stock.api.Stock
import kotlinx.android.synthetic.main.stock_cell.view.*

class StockListViewAdapter(private var stockItems: MutableList<Stock>, context : Context) : BaseAdapter() {
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, oldView: View?, parent: ViewGroup?): View {
        val view : View = //if yes, use the oldview
            oldView ?: //check if we get a view to recycle
            layoutInflater.inflate(R.layout.stock_cell, null)
        val stock = getItem(position) //get the data for this index
        view.name.text = "${stock.symbol} (${stock.latestTradingDay})"
        view.value.text = stock.price.toString()
        return view
    }

    override fun getItem(position: Int): Stock {
        return stockItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return stockItems.size
    }
}
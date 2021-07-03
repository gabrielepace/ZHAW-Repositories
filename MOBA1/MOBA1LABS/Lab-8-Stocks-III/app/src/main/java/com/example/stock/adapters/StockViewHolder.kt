package com.example.stock.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.R
import com.example.stock.api.Stock

class StockViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.stock_cell, parent, false)) {
    private var nameView: TextView? = null
    private var valueView: TextView? = null

    init {
        nameView = itemView.findViewById(R.id.name)
        valueView = itemView.findViewById(R.id.value)
    }

    fun bind(stock: Stock) {
        nameView?.text = "${stock.symbol} (${stock.latestTradingDay})"
        valueView?.text = stock.price.toString()
    }
}
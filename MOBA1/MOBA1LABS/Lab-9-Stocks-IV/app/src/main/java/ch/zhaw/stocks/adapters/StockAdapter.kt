package ch.zhaw.stocks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.zhaw.stocks.api.Stock

class StockAdapter (private val list : List<Stock>) : RecyclerView.Adapter<StockViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StockViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock: Stock = list[position]
        holder.bind(stock)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
package ch.zhaw.stocks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ch.zhaw.stocks.R
import ch.zhaw.stocks.api.Stock
import kotlinx.android.synthetic.main.stock_cell.view.*

class StockListViewAdapter(var stockItems: MutableList<Stock>, val context : Context) : BaseAdapter() {
    var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, oldView: View?, parent: ViewGroup?): View {
        var view : View
        if (oldView == null) { //check if we get a view to recycle
            view = layoutInflater.inflate(R.layout.stock_cell, null)
        }
        else { //if yes, use the oldview
            view = oldView
        }
        val stock = getItem(position) //get the data for this index
        view.name.text = "${stock.symbol} (${stock.latestTradingDay})"
        view.value.text = stock.price.toString()
        return view
    }

    override fun getItem(position: Int): Stock {
        return stockItems.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return stockItems.size
    }
}
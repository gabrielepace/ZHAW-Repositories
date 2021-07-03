package com.example.stock

import android.content.Context
import android.os.Bundle
import android.view.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    // API-Key: HIAM8SZJ84C9VPXQ
    // https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=GOOG&apikey=HIAM8SZJ84C9VPXQ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Only 4 Elements, because the API limits to 5 cells
        var items = mutableListOf(
                Stock("Apple", "AAPL", 0.0),
                Stock("Microsoft", "MSFT", 214.36),
                Stock("Google", "GOOGL", 1519.45),
                Stock("Salesforce", "CRM", 255.52),
//                Stock("Facebook", "FB", 260.02),
//                Stock("Amazon", "AMZN", 3201.86),
//                Stock("eBay", "EBAY", 54.05),
//                Stock("Twitter", "TWTR", 45.41),
//                Stock("Snapchat", "SNAP", 28.11)
        )

        val stockAdapter = StockAdapter(items)
        stocklist.adapter = stockAdapter
        stocklist.layoutManager = LinearLayoutManager(this)

        var correctedItems = mutableListOf<Stock>()

        val requestQueue = Volley.newRequestQueue(this)
        val hostUrl = "https://www.alphavantage.co/";
        val apikey = "HIAM8SZJ84C9VPXQ";

        var numberOfRequests = items.size;
        var sentRequests = 0
        items.forEach {
            val request = StringRequest(
                Request.Method.GET,
                hostUrl + "query?function=GLOBAL_QUOTE&symbol="+it.symbol+"&apikey=" + apikey,
                { response ->
                    val stockListJsonItem = Klaxon().parse<StockListJson>(response)
                    val stockItem = Stock(it.name,it.symbol,stockListJsonItem!!.function.value.toDouble())
                    correctedItems.add(stockItem)
                    numberOfRequests--
                    sentRequests++

                    if (numberOfRequests == 0) {
                        val newAdapter = StockAdapter(correctedItems)
                        stocklist.adapter = newAdapter
                        newAdapter.notifyDataSetChanged()
                    }
                },
                {})
            requestQueue.add(request)
        }
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

class StockListJson(
    @Json(name = "Global Quote")
    val function: StockJson
) {}

class StockJson(
    @Json(name = "01. symbol")
    val symbol: String,
    @Json(name = "05. price")
    val value: String
){}

class Stock(var name: String, var symbol: String, var value: Double) {
}

class StockViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.stock_cell,parent,false)) {
    private var nameView: TextView? = null
    private var valueView: TextView? = null

    init {
        nameView = itemView.findViewById(R.id.name)
        valueView = itemView.findViewById(R.id.value)
    }
    fun bind(stock: Stock) {
        nameView?.text = stock.name
        valueView?.text = stock.value.toString()
    }
}

class StockAdapter(private var stocks: MutableList<Stock>) : RecyclerView.Adapter<StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StockViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock: Stock = stocks[position]
        holder.bind(stock)
    }

    override fun getItemCount(): Int {
        return stocks.size
    }

}



package com.example.stock.api

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class StockEndpoint {
    private val symbols = listOf("IBM", "AAPL") //, "MSFT", "MSI")
    private val endpoint = "https://www.alphavantage.co/"
    private val apiKey = "HIAM8SZJ84C9VPXQ"

    fun getStockData(
        context: Context,
        items: MutableList<Stock>
    ) {
        val requestQueue = Volley.newRequestQueue(context)

        for (symbol in symbols) {
            val endpointURL =
                "${endpoint}query?function=GLOBAL_QUOTE&symbol=${symbol}&apikey=${apiKey}"
            val request = StringRequest(
                Request.Method.GET, endpointURL,
                { response ->
                    val stock = Gson().fromJson(response, Stocks::class.java).stock
                    items.add(stock)
//                    items.postValue(items.value)
                },
                {
                    Log.w("Warning", "URL / JSON not found or failed")
                })

            requestQueue.add(request)
        }
    }
}

package ch.zhaw.stocks.api

import com.google.gson.annotations.SerializedName

class StockDetails(
    @SerializedName("Meta Data") val stockMeta: StockMeta,
    @SerializedName("Time Series (Daily)") val stockDetail: Map<String, StockDetail>
) {}

class StockMeta(
    @SerializedName("1. Information")
    val information: String,
    @SerializedName("2. Symbol")
    val symbol: String,
    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String,
    @SerializedName("4. Output Size")
    val outputSize: String,
    @SerializedName("5. Time Zone")
    val timeZone: String
)

class StockDetail(
    @SerializedName("1. open")
    val open: Double,
    @SerializedName("2. high")
    val high: Double,
    @SerializedName("3. low")
    val low: Double,
    @SerializedName("4. close")
    val close: Float,
    @SerializedName("5. volume")
    val volume: Int
) {}



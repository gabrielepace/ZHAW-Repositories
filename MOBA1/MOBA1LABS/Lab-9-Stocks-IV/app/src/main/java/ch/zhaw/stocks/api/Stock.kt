package ch.zhaw.stocks.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Stock(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @SerializedName("01. symbol")
    val symbol: String,
    @SerializedName("02. open")
    val open: Double,
    @SerializedName("03. high")
    val high: Double,
    @SerializedName("04. low")
    val low: Double,
    @SerializedName("05. price")
    val price: Double,
    @SerializedName("06. volume")
    val volume: Double,
    @ColumnInfo(name = "latest_trading_day")
    @SerializedName("07. latest trading day")
    val latestTradingDay: String,
    @ColumnInfo(name = "previous_close")
    @SerializedName("08. previous close")
    val previousClose: Double,
    @SerializedName("09. change")
    val change: Double,
    @ColumnInfo(name = "change_percent")
    @SerializedName("10. change percent")
    val changePercent: String
) {}

class Stocks(@SerializedName("Global Quote") val stock: Stock) {
}
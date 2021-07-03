package ch.zhaw.stocks

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ch.zhaw.stocks.api.StockDetails
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlinx.android.synthetic.main.content_detail.*
import lecho.lib.hellocharts.formatter.SimpleLineChartValueFormatter
import lecho.lib.hellocharts.gesture.ZoomType
import lecho.lib.hellocharts.model.*


class DetailActivity : AppCompatActivity() {
    private val symbols = listOf("IBM", "AAPL") //, "MSFT", "MSI")
    private val endpoint = "https://www.alphavantage.co/"
    private val apiKey = "AHC1KVZZRW4BG5EO"
    private val nrvalues = 14
    private val chartColor = Color.BLUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))

        callStockDetails()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            finish()
        }
    }

    fun loadLineChart(stockDetails: StockDetails) {
        val data = LineChartData()

        val axisXValues: MutableList<AxisValue> = mutableListOf()
        val values: MutableList<PointValue> = mutableListOf()

        var i = nrvalues
        for (stockdetail in stockDetails.stockDetail) {
            values.add(PointValue(i.toFloat(), stockdetail.value.close))
            axisXValues.add(AxisValue(i.toFloat()).setLabel(stockdetail.key.substring(startIndex = 5)))

            if (i == 0) {
                break
            }
            i--
        }

        val line: Line = Line(values)
            .setColor(chartColor)
            .setCubic(false)
            .setHasLabels(true)
            .setFormatter(SimpleLineChartValueFormatter(2))
        val lines: MutableList<Line> = mutableListOf()
        lines.add(line)
        data.lines = lines

        val axisX = Axis(axisXValues)
            .setTextColor(chartColor)
            .setLineColor(chartColor)
            .setTextSize(16)
        data.setAxisXBottom(axisX);

//        stockChart.setContainerScrollEnabled(true, ContainerScrollType.VERTICAL)
        stockChart.setInteractive(true)
        stockChart.setZoomType(ZoomType.HORIZONTAL);
        stockChart.lineChartData = data
    }

    fun callStockDetails() {
        val requestQueue = Volley.newRequestQueue(this)
        val endpointURL =
            "${endpoint}query?function=TIME_SERIES_DAILY&symbol=${symbols[0]}&apikey=${apiKey}"
        val request = StringRequest(
            Request.Method.GET, endpointURL,
            { response ->
                val stockDetails = Gson().fromJson(response, StockDetails::class.java)
                loadLineChart(stockDetails)
            },
            {
                Log.w("Warning", "URL / JSON not found or failed")
            })
        requestQueue.add(request)
    }
}
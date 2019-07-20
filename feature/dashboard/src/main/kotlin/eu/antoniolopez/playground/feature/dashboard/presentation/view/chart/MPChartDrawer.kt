package eu.antoniolopez.playground.feature.dashboard.presentation.view.chart

import android.graphics.Color
import android.text.format.DateUtils
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import eu.antoniolopez.playground.core.view.extension.getCompatColor
import eu.antoniolopez.playground.feature.dashboard.R
import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartData
import eu.antoniolopez.playground.feature.dashboard.presentation.view.chart.mapper.LineDataMapper
import java.io.InvalidClassException

class MPChartDrawer(
    private val lineDataMapper: LineDataMapper
) : ChartDrawer {

    override fun draw(chartView: View, chartData: ChartData) {
        if (chartView !is LineChart) {
            throw InvalidClassException("Expected LineChart View")
        }
        val chart: LineChart = chartView
        chart.apply {
            description.isEnabled = false
            setNoDataText(context.getString(R.string.no_data))
            legend.isEnabled = false
            setTouchEnabled(false)
            isDragEnabled = false
            setScaleEnabled(false)
            setDrawGridBackground(false)
            isHighlightPerDragEnabled = false
            setPinchZoom(false)
            setBackgroundColor(Color.TRANSPARENT)
            clear()
        }
        chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawAxisLine(true)
            setDrawGridLines(false)
            labelCount = 5
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return DateUtils.formatDateTime(
                        chart.context,
                        value.toLong() * 1000,
                        DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_NO_MONTH_DAY or DateUtils.FORMAT_ABBREV_ALL
                    )
                }
            }
        }
        chart.axisLeft.apply {
            axisMinimum = 0f
        }
        chart.axisRight.apply {
            setDrawLabels(false)
            setDrawAxisLine(false)
            setDrawGridLines(false)
        }
        val lineDataSet: LineDataSet = lineDataMapper.apply(chartData).apply {
            setDrawCircles(false)
            color = chart.context.getCompatColor(R.color.colorAccent)
            lineWidth = 3f
        }
        val data = LineData(lineDataSet)
        chart.data = data
    }
}

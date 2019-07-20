package eu.antoniolopez.playground.feature.dashboard.presentation.view.chart.mapper

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartData
import io.reactivex.functions.Function

class LineDataMapper : Function<ChartData, LineDataSet> {

    override fun apply(chartData: ChartData): LineDataSet =
        LineDataSet(
            chartData.values.map {
                Entry(it.date.timeInMillis.toFloat(), it.value.toFloat())
            }, ""
        )
}

package eu.antoniolopez.playground.feature.dashboard.presentation.view.chart

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.functions.Function

class LineDataMapper : Function<Market, LineData> {

    override fun apply(market: Market): LineData {
        val lineDataSet = LineDataSet(
            market.values.map {
                Entry(it.date.timeInMillis.toFloat(), it.value.toFloat())
            }, "DataSet 1"
        )
        return LineData(lineDataSet)
    }
}

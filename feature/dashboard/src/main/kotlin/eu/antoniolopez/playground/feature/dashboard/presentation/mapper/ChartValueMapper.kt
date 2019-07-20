package eu.antoniolopez.playground.feature.dashboard.presentation.mapper

import eu.antoniolopez.playground.feature.dashboard.domain.model.Value
import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartValue
import io.reactivex.functions.Function

class ChartValueMapper : Function<List<Value>, List<ChartValue>> {
    override fun apply(list: List<Value>): List<ChartValue> =
        list.map {
            ChartValue(
                date = it.date,
                value = it.value
            )
        }
}

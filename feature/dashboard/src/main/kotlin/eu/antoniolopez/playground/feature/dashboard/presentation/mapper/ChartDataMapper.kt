package eu.antoniolopez.playground.feature.dashboard.presentation.mapper

import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartData
import io.reactivex.functions.Function

class ChartDataMapper(
    private val chartValueMapper: ChartValueMapper
) : Function<Market, ChartData> {

    override fun apply(market: Market): ChartData =
        ChartData(
            values = chartValueMapper.apply(market.values)
        )
}

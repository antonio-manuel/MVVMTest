package eu.antoniolopez.playground.feature.dashboard.data.mapper

import eu.antoniolopez.playground.api.market.model.DTOMarket
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.functions.Function

class MarketMapper(
    private val valueMapper: ValueMapper
) : Function<DTOMarket, Market> {

    override fun apply(dtoMarket: DTOMarket): Market =
        Market(
            unit = dtoMarket.unit,
            period = dtoMarket.period,
            values = valueMapper.apply(dtoMarket.values)
        )
}

package eu.antoniolopez.playground.feature.dashboard.data.datasource

import eu.antoniolopez.playground.api.market.MarketApi
import eu.antoniolopez.playground.feature.dashboard.data.mapper.MarketMapper
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.Single

class ApiMarketDataSource(
    private val marketApi: MarketApi,
    private val marketMapper: MarketMapper
) : MarketDataSource {

    override val market: Single<Market>
        get() = marketApi
            .getMarket()
            .map { marketMapper.apply(it) }
}

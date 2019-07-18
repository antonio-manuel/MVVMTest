package eu.antoniolopez.playground.feature.dashboard.data.repository

import eu.antoniolopez.playground.core.data.cache.Cache
import eu.antoniolopez.playground.feature.dashboard.data.datasource.MarketDataSource
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.Single

class DataMarketRepository(
    private val marketDataSource: MarketDataSource,
    private val cache: Cache<Market>
) : MarketRepository {

    override val market: Single<Market>
        get() = loadMarket()

    private fun loadMarket(): Single<Market> =
        cache.getValue().fold(
            ifFailure = {
                marketDataSource.market
                    .doOnSuccess {
                        cache.updateValue(it)
                    }
            },
            ifSuccess = { Single.just(it) }
        )
}

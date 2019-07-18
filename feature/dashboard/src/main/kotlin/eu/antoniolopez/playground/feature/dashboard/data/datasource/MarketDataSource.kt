package eu.antoniolopez.playground.feature.dashboard.data.datasource

import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.Single

interface MarketDataSource {
    val market: Single<Market>
}

package eu.antoniolopez.playground.feature.dashboard.data.repository

import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.Single

interface MarketRepository {
    val market: Single<Market>
}

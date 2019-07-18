package eu.antoniolopez.playground.api.market

import eu.antoniolopez.playground.api.market.model.DTOMarket
import io.reactivex.Single

interface MarketApi {
    fun getMarket(): Single<DTOMarket>
}

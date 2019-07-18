package eu.antoniolopez.playground.api.market

import eu.antoniolopez.playground.api.BaseRetrofitApi
import eu.antoniolopez.playground.api.market.model.DTOMarket
import io.reactivex.Single

class RetrofitMarketApi(
    private val marketRetrofitService: MarketRetrofitService
) : MarketApi, BaseRetrofitApi() {

    override fun getMarket(): Single<DTOMarket> =
        marketRetrofitService.getMarket()
}

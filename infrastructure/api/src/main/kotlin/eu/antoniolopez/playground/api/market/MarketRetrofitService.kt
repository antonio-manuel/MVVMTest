package eu.antoniolopez.playground.api.market

import eu.antoniolopez.playground.api.market.model.DTOMarket
import io.reactivex.Single
import retrofit2.http.GET

interface MarketRetrofitService {
    companion object {
        private const val BASE_PATH = "charts"
        const val MARKET_PATH = "$BASE_PATH/market-price"
    }

    @GET(MARKET_PATH)
    fun getMarket(): Single<DTOMarket>
}

package eu.antoniolopez.playground.api.market.di

import eu.antoniolopez.playground.api.market.MarketApi
import eu.antoniolopez.playground.api.market.MarketRetrofitService
import eu.antoniolopez.playground.api.market.RetrofitMarketApi
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val marketApiModule = Kodein.Module(name = "marketApiModule") {
    bind<MarketRetrofitService>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(MarketRetrofitService::class.java)
    }
    bind<MarketApi>() with singleton { RetrofitMarketApi(marketRetrofitService = instance()) }
}

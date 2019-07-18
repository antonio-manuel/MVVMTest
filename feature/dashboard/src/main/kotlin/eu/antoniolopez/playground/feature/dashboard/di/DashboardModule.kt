package eu.antoniolopez.playground.feature.dashboard.di

import eu.antoniolopez.playground.core.data.cache.Cache
import eu.antoniolopez.playground.core.data.cache.MemoryCache
import eu.antoniolopez.playground.feature.dashboard.data.datasource.ApiMarketDataSource
import eu.antoniolopez.playground.feature.dashboard.data.datasource.MarketDataSource
import eu.antoniolopez.playground.feature.dashboard.data.mapper.MarketMapper
import eu.antoniolopez.playground.feature.dashboard.data.mapper.ValueMapper
import eu.antoniolopez.playground.feature.dashboard.data.repository.DataMarketRepository
import eu.antoniolopez.playground.feature.dashboard.data.repository.MarketRepository
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import eu.antoniolopez.playground.feature.dashboard.domain.usecase.GetMarketUseCase
import eu.antoniolopez.playground.feature.dashboard.presentation.view.chart.LineDataMapper
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

internal val dashboardModule = Kodein.Module(name = "dashboardModule") {
    bind<ValueMapper>() with provider { ValueMapper() }
    bind<MarketMapper>() with provider { MarketMapper(instance()) }
    bind<LineDataMapper>() with provider { LineDataMapper() }
    bind<MarketDataSource>() with provider { ApiMarketDataSource(instance(), instance()) }
    bind<Cache<Market>>() with provider { MemoryCache(instance(), instance(), Market::class.java) }
    bind<MarketRepository>() with provider { DataMarketRepository(instance(), instance()) }
    bind<GetMarketUseCase>() with provider { GetMarketUseCase(instance()) }
    bind<DashboardViewModel>() with provider { DashboardViewModel(instance()) }
}

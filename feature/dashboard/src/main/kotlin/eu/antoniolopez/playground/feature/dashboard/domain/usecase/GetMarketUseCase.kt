package eu.antoniolopez.playground.feature.dashboard.domain.usecase

import eu.antoniolopez.playground.feature.dashboard.data.repository.MarketRepository
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.Single

class GetMarketUseCase(
    private val marketRepository: MarketRepository
) {
    fun execute(): Single<Market> =
        marketRepository.market
}

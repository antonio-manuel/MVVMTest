package eu.antoniolopez.playground.feature.dashboard.domain.usecase

import eu.antoniolopez.playground.feature.dashboard.data.repository.MarketRepository
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetMarketUseCase(
    private val marketRepository: MarketRepository
) {
    fun execute(): Single<Market> =
        marketRepository.market
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
}

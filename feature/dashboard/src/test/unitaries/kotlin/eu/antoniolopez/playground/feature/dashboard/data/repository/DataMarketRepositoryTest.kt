package eu.antoniolopez.playground.feature.dashboard.data.repository

import eu.antoniolopez.playground.core.data.cache.Cache
import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.exceptions.NotFoundException
import eu.antoniolopez.playground.feature.dashboard.data.datasource.MarketDataSource
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test
import timber.log.Timber

class DataMarketRepositoryTest : UnitTest() {

    private lateinit var sut: DataMarketRepository
    private val mockMarketDataSource: MarketDataSource = mockk()
    private val mockCache: Cache<Market> = mockk(relaxed = true)

    override fun onPrepareBeforeEachTest() {
        super.onPrepareBeforeEachTest()
        sut = DataMarketRepository(marketDataSource = mockMarketDataSource, cache = mockCache)
    }

    @Test
    fun `when cache IS valid then datasource IS NOT called`() {
        every { mockCache.getValue() } returns Single.just(mockk())

        getValue()

        verify(inverse = true) { mockMarketDataSource.market }
    }

    @Test
    fun `when cache IS NOT valid then datasource IS called`() {
        every { mockCache.getValue() } returns Single.error(NotFoundException())
        every { mockMarketDataSource.market } returns Single.just(mockk())

        getValue()

        verify { mockMarketDataSource.market }
    }

    @Test
    fun `when datasource returns success then cache IS updated`() {
        every { mockCache.getValue() } returns Single.error(NotFoundException())
        every { mockMarketDataSource.market } returns Single.just(mockk())

        getValue()

        verify { mockCache.updateValue(any()) }
    }

    @Test
    fun `when datasource returns failure then cache IS NOT updated`() {
        every { mockCache.getValue() } returns Single.error(NotFoundException())
        every { mockMarketDataSource.market } returns Single.error(NotFoundException())

        getValue()

        verify(inverse = true) { mockCache.updateValue(any()) }
    }

    private fun getValue() {
        sut.market
            .subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
            .subscribe(
                { result ->
                    Timber.d("result success $result")
                },
                { error ->
                    Timber.d("result error ${error.message}")
                }
            )
    }
}

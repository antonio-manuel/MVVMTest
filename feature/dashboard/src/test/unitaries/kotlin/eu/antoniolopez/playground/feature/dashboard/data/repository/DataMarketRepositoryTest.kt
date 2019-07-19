package eu.antoniolopez.playground.feature.dashboard.data.repository

import android.content.res.Resources
import eu.antoniolopez.playground.core.data.cache.Cache
import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.feature.dashboard.data.datasource.MarketDataSource
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.jupiter.api.Test

class DataMarketRepositoryTest : UnitTest() {

    private lateinit var sut: DataMarketRepository
    private val mockMarketDataSource: MarketDataSource = mockk()
    private val mockCache: Cache<Market> = mockk(relaxed = true)

    override fun onPrepareBeforeEachTest() {
        super.onPrepareBeforeEachTest()
        every { mockMarketDataSource.market } returns Single.just(mockk())
        sut = DataMarketRepository(marketDataSource = mockMarketDataSource, cache = mockCache)
    }

    @Test
    fun `when cache IS valid then datasource IS NOT called`() {
        every { mockCache.getValue() } returns Single.just(mockk())

        sut.market.blockingGet()

        verify(inverse = true) { mockMarketDataSource.market }
    }

    @Test
    fun `when cache IS NOT valid then datasource IS called`() {
        every { mockCache.getValue() } returns Single.error(Resources.NotFoundException())

        sut.market.blockingGet()

        verify {
            mockMarketDataSource.market
            mockCache.updateValue(any())
        }
    }
}

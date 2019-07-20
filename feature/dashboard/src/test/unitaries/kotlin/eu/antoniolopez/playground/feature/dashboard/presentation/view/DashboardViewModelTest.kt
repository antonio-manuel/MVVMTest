package eu.antoniolopez.playground.feature.dashboard.presentation.view

import android.content.res.Resources
import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.feature.dashboard.domain.usecase.GetMarketUseCase
import eu.antoniolopez.playground.feature.dashboard.presentation.mapper.ChartDataMapper
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardState
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardViewModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test

class DashboardViewModelTest : UnitTest() {

    private lateinit var sut: DashboardViewModel
    private val mockGetMarketUseCase: GetMarketUseCase = mockk()
    private val mockChartDataMapper: ChartDataMapper = mockk(relaxed = true)

    @Test
    fun `when market received then posts the value`() {
        every { mockGetMarketUseCase.execute() } returns Single.just(mockk())

        sut = DashboardViewModel(mockGetMarketUseCase, mockChartDataMapper)

        assertTrue(sut.state.value is DashboardState.DataFetched)
    }

    @Test
    fun `when error received then posts error`() {
        every { mockGetMarketUseCase.execute() } returns Single.error(Resources.NotFoundException())

        sut = DashboardViewModel(mockGetMarketUseCase, mockChartDataMapper)

        assertTrue(sut.state.value is DashboardState.Error)
    }
}

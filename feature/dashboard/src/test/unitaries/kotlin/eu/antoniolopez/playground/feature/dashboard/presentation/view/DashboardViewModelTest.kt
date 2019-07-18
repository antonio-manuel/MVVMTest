package eu.antoniolopez.playground.feature.dashboard.presentation.view

import eu.antoniolopez.playground.core.testing.UnitTest
import eu.antoniolopez.playground.feature.dashboard.domain.usecase.GetMarketUseCase
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

    @Test
    fun `when market received then posts `() {
        every { mockGetMarketUseCase.execute() } returns Single.just(mockk())

        sut = DashboardViewModel(mockGetMarketUseCase)

        assertTrue(sut.state.value is DashboardState.RenderData)
    }
}

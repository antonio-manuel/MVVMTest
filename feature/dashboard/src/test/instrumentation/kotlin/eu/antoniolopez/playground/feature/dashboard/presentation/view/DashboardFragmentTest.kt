package eu.antoniolopez.playground.feature.dashboard.presentation.view

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.feature.dashboard.R
import eu.antoniolopez.playground.feature.dashboard.di.dashboardComponent
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import eu.antoniolopez.playground.feature.dashboard.presentation.view.chart.LineDataMapper
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardState
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class DashboardFragmentTest : InstrumentationUnitTest() {

    private val mockViewModel: DashboardViewModel = mockk(relaxed = true)
    private val mockLineDataMapper: LineDataMapper = mockk(relaxed = true)
    private lateinit var liveData: MutableLiveData<DashboardState>

    override fun onRequestFragment(): DashboardFragment = DashboardFragment.newInstance()

    override fun onPrepareInjection() {
        dashboardComponent.addConfig {
            bind<DashboardViewModel>(overrides = true) with singleton { mockViewModel }
            bind<LineDataMapper>(overrides = true) with singleton { mockLineDataMapper }
        }
    }

    override fun onPrepareBeforeEachTest() {
        super.onPrepareBeforeEachTest()
        liveData = MutableLiveData()
        every { mockViewModel.state } returns liveData
    }

    @Test
    fun whenErrorStateThenCardIsInvisibleAndErrorDisplayed() {
        val error = "ERROR"
        liveData.postValue(DashboardState.ShowError(error))

        onView(withId(R.id.card)).check(matches(not(isDisplayed())))
        onView(withId(R.id.textToBeChanged)).check((matches(withText(error))))
    }

    @Test
    fun whenRenderDataStateThenCardIsVisible() {
        val market: Market = mockk(relaxed = true)
        liveData.postValue(DashboardState.RenderData(market))

        verify { mockLineDataMapper.apply(market) }
        onView(withId(R.id.card)).check(matches(isDisplayed()))
    }
}

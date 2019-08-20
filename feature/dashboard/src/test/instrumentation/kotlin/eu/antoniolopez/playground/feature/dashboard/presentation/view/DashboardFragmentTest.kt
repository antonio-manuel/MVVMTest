package eu.antoniolopez.playground.feature.dashboard.presentation.view

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.feature.dashboard.R
import eu.antoniolopez.playground.feature.dashboard.di.dashboardComponent
import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartData
import eu.antoniolopez.playground.feature.dashboard.presentation.view.chart.ChartDrawer
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
    private val mockChartDrawer: ChartDrawer = mockk(relaxed = true)
    private lateinit var liveData: MutableLiveData<DashboardState>

    override fun onRequestFragment(): DashboardFragment = DashboardFragment.newInstance()

    override fun onPrepareInjection() {
        dashboardComponent.addConfig {
            bind<DashboardViewModel>(overrides = true) with singleton { mockViewModel }
            bind<ChartDrawer>(overrides = true) with singleton { mockChartDrawer }
        }
    }

    override fun onPrepareBeforeEachTest() {
        super.onPrepareBeforeEachTest()
        liveData = MutableLiveData()
        every { mockViewModel.state } returns liveData
    }

    @Test
    fun whenLoadingStateThenLoadingIsShown() {
        liveData.postValue(DashboardState.Loading)

        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun whenErrorStateThenCardIsInvisibleAndErrorDisplayed() {
        val error = "ERROR"
        liveData.postValue(DashboardState.Error(error))

        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
        onView(withId(R.id.chart)).check(matches(not(isDisplayed())))
        onView(withId(R.id.no_data)).check(matches(isDisplayed()))
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(error)))
    }

    @Test
    fun whenRenderDataStateThenCardIsVisible() {
        val chartData: ChartData = mockk(relaxed = true)
        liveData.postValue(DashboardState.DataFetched(chartData))

        verify { mockChartDrawer.draw(any(), any()) }
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
        onView(withId(R.id.chart)).check(matches(isDisplayed()))
        onView(withId(R.id.no_data)).check(matches(not(isDisplayed())))
    }
}

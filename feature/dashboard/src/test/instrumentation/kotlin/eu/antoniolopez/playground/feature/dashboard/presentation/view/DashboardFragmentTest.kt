package eu.antoniolopez.playground.feature.dashboard.presentation.view

import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.feature.dashboard.di.dashboardComponent
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardViewModel
import io.mockk.mockk
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class DashboardFragmentTest : InstrumentationUnitTest() {

    private val mockViewModel: DashboardViewModel = mockk(relaxed = true)

    override fun onRequestFragment(): DashboardFragment = DashboardFragment.newInstance()

    override fun onPrepareInjection() {
        dashboardComponent.addConfig {
            bind<DashboardViewModel>(overrides = true) with singleton { mockViewModel }
        }
    }
}

package eu.antoniolopez.playground.presentation.view

import eu.antoniolopez.playground.core.view.testing.InstrumentationUnitTest
import eu.antoniolopez.playground.di.appComponent
import eu.antoniolopez.playground.navigation.Navigator
import eu.antoniolopez.playground.presentation.viewmodel.MainViewModel
import io.mockk.mockk
import org.junit.Test
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class AppFragmentTest : InstrumentationUnitTest() {

    private val mockViewModel: MainViewModel = mockk(relaxed = true)
    private val mockNavigator: Navigator = mockk(relaxed = true)

    override fun onRequestFragment(): MainFragment = MainFragment.newInstance()

    override fun onPrepareInjection() {
        appComponent.addConfig {
            bind<MainViewModel>(overrides = true) with singleton { mockViewModel }
            bind<Navigator>(overrides = true) with singleton { mockNavigator }
        }
    }

    @Test
    fun testThings() {
        assert(true)
    }
}

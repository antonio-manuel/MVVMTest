package eu.antoniolopez.playground.feature.dashboard.presentation.view

import androidx.fragment.app.Fragment
import eu.antoniolopez.playground.core.view.BaseActivity

class DashboardActivity : BaseActivity() {
    override fun onRequestFragment(): Fragment = DashboardFragment.newInstance()
}

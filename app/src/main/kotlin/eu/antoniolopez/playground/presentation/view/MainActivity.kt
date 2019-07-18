package eu.antoniolopez.playground.presentation.view

import androidx.fragment.app.Fragment
import eu.antoniolopez.playground.core.view.BaseActivity

class MainActivity : BaseActivity() {
    override fun onRequestFragment(): Fragment = MainFragment.newInstance()
}


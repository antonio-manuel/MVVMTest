package eu.antoniolopez.playground.presentation.view

import android.os.Bundle
import eu.antoniolopez.playground.R
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.getViewModel
import eu.antoniolopez.playground.core.view.extension.observe
import eu.antoniolopez.playground.di.appComponent
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.Navigator
import eu.antoniolopez.playground.presentation.viewmodel.MainState
import eu.antoniolopez.playground.presentation.viewmodel.MainViewModel
import org.kodein.di.direct
import org.kodein.di.generic.instance

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val navigator: Navigator by appComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.main_fragment

    override fun onViewReady(savedInstanceState: Bundle?) {
        viewModel = getViewModel { appComponent.direct.instance() }
        observe(viewModel.state, ::processState)
    }

    private fun processState(mainState: MainState?) {
        when (mainState) {
            is MainState.Navigate -> navigateTo(mainState.command)
        }
    }

    private fun navigateTo(command: NavigationCommand) {
        navigator.navigate(requireContext(), command)
        requireActivity().finish()
    }
}

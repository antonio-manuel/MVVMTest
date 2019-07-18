package eu.antoniolopez.playground.presentation.viewmodel

import eu.antoniolopez.playground.core.view.BaseViewModel
import eu.antoniolopez.playground.navigation.command.dashboard.dashboardNavigationCommand

class MainViewModel : BaseViewModel<MainState>() {

    init {
        mutableState.postValue(MainState.Navigate(dashboardNavigationCommand()))
    }
}

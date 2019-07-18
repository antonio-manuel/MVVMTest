package eu.antoniolopez.playground.presentation.viewmodel

import eu.antoniolopez.playground.navigation.NavigationCommand

sealed class MainState {
    class Navigate(val command: NavigationCommand) : MainState()
}

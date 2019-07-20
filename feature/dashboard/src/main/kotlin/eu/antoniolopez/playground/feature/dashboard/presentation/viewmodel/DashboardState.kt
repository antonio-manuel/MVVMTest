package eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel

import eu.antoniolopez.playground.feature.dashboard.domain.model.Market

sealed class DashboardState {
    object Loading : DashboardState()
    class DataFetched(val market: Market) : DashboardState()
    class Error(val message: String = "") : DashboardState()
}

package eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel

import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartData

sealed class DashboardState {
    object Loading : DashboardState()
    class DataFetched(val chartData: ChartData) : DashboardState()
    class Error(val message: String = "") : DashboardState()
}

package eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel

import eu.antoniolopez.playground.feature.dashboard.domain.model.Market

sealed class DashboardState {
    class RenderData(val market: Market) : DashboardState()
    class ShowError(val message: String = "") : DashboardState()
}

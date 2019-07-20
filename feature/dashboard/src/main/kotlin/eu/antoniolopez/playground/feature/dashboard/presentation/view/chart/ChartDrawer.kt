package eu.antoniolopez.playground.feature.dashboard.presentation.view.chart

import android.view.View
import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartData

interface ChartDrawer {
    fun draw(chartView: View, chartData: ChartData)
}

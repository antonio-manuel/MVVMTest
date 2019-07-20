package eu.antoniolopez.playground.feature.dashboard.presentation.view

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.getViewModel
import eu.antoniolopez.playground.core.view.extension.observe
import eu.antoniolopez.playground.feature.dashboard.R
import eu.antoniolopez.playground.feature.dashboard.di.dashboardComponent
import eu.antoniolopez.playground.feature.dashboard.presentation.model.ChartData
import eu.antoniolopez.playground.feature.dashboard.presentation.view.chart.ChartDrawer
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardState
import eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.kodein.di.direct
import org.kodein.di.generic.instance

class DashboardFragment : BaseFragment() {

    companion object {
        fun newInstance(): DashboardFragment = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel
    private val chartDrawer: ChartDrawer by dashboardComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.dashboard_fragment

    override fun onViewReady(savedInstanceState: Bundle?) {
        viewModel = getViewModel { dashboardComponent.direct.instance() }
        observe(viewModel.state, ::processState)
    }

    private fun processState(dashboardState: DashboardState?) {
        when (dashboardState) {
            is DashboardState.Loading -> showLoading()
            is DashboardState.DataFetched -> renderMarket(dashboardState.chartData)
            is DashboardState.Error -> renderError(dashboardState.message)
        }
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    private fun renderMarket(chartData: ChartData) {
        chartDrawer.draw(chart, chartData)
        chart.visibility = View.VISIBLE
        no_data.visibility = View.GONE
        progress.visibility = View.GONE
    }

    private fun renderError(text: String) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()
        chart.visibility = View.GONE
        no_data.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }
}

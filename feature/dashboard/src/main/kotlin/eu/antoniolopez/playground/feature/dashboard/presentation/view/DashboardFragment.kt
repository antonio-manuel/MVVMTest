package eu.antoniolopez.playground.feature.dashboard.presentation.view

import android.os.Bundle
import android.view.View
import eu.antoniolopez.playground.core.view.BaseFragment
import eu.antoniolopez.playground.core.view.extension.getViewModel
import eu.antoniolopez.playground.core.view.extension.observe
import eu.antoniolopez.playground.feature.dashboard.R
import eu.antoniolopez.playground.feature.dashboard.di.dashboardComponent
import eu.antoniolopez.playground.feature.dashboard.domain.model.Market
import eu.antoniolopez.playground.feature.dashboard.presentation.view.chart.LineDataMapper
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
    private val lineDataMapper: LineDataMapper by dashboardComponent.instance()

    override fun onRequestLayoutResourceId(): Int = R.layout.dashboard_fragment

    override fun onViewReady(savedInstanceState: Bundle?) {
        viewModel = getViewModel { dashboardComponent.direct.instance() }
        observe(viewModel.state, ::processState)
    }

    private fun processState(dashboardState: DashboardState?) {
        when (dashboardState) {
            is DashboardState.RenderData -> renderMarket(dashboardState.market)
            is DashboardState.ShowError -> renderError(dashboardState.message)
        }
    }

    private fun renderMarket(market: Market) {
        chart.data = lineDataMapper.apply(market)
        card.visibility = View.VISIBLE
    }

    private fun renderError(text: String) {
        textToBeChanged.text = text
        card.visibility = View.GONE
    }
}

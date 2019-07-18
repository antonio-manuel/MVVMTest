package eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel

import eu.antoniolopez.playground.core.view.BaseViewModel
import eu.antoniolopez.playground.feature.dashboard.domain.usecase.GetMarketUseCase
import io.reactivex.disposables.Disposable

class DashboardViewModel(
    private val getMarketUseCase: GetMarketUseCase
) : BaseViewModel<DashboardState>() {

    init {
        compositeDisposable.add(getMarketData())
    }

    private fun getMarketData(): Disposable =
        getMarketUseCase.execute()
            .subscribe(
                { result ->
                    mutableState.postValue(DashboardState.RenderData(result))
                },
                { error ->
                    mutableState.postValue(DashboardState.ShowError(error.message ?: ""))
                }
            )
}

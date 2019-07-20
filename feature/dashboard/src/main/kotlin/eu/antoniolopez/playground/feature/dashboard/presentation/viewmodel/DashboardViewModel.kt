package eu.antoniolopez.playground.feature.dashboard.presentation.viewmodel

import eu.antoniolopez.playground.core.view.BaseViewModel
import eu.antoniolopez.playground.feature.dashboard.domain.usecase.GetMarketUseCase
import io.reactivex.disposables.Disposable

class DashboardViewModel(
    private val getMarketUseCase: GetMarketUseCase
) : BaseViewModel<DashboardState>() {

    init {
        mutableState.postValue(DashboardState.Loading)
        compositeDisposable.add(getMarketData())
    }

    private fun getMarketData(): Disposable =
        getMarketUseCase.execute()
            .subscribe(
                { result ->
                    mutableState.postValue(DashboardState.DataFetched(result))
                },
                { error ->
                    mutableState.postValue(DashboardState.Error(error.message ?: ""))
                }
            )
}

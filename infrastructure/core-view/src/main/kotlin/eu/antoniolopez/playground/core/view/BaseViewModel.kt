package eu.antoniolopez.playground.core.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<T> : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    protected val mutableState: MutableLiveData<T> = MutableLiveData()
    val state: LiveData<T>
        get() = mutableState

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

package eu.antoniolopez.playground.core.view.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.*

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

inline fun <reified T : ViewModel> Fragment.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.withViewModel(body: T.() -> Unit): T {
    val vm = getViewModel<T>()
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.withViewModel(
    crossinline factory: () -> T,
    body: T.() -> Unit
): T {
    val vm = getViewModel(factory)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {
    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }
    return ViewModelProviders.of(this, vmFactory)[T::class.java]
}

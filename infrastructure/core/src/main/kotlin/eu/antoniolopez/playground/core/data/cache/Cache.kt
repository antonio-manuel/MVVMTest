package eu.antoniolopez.playground.core.data.cache

import io.reactivex.Single

interface Cache<T> {
    fun updateValue(value: T)
    fun getValue(): Single<T>
}

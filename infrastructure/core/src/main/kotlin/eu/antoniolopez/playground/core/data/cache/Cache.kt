package eu.antoniolopez.playground.core.data.cache

import arrow.core.Try

interface Cache<T> {
    fun updateValue(value: T)
    fun getValue(): Try<T>
}

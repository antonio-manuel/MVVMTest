package eu.antoniolopez.playground.feature.dashboard.data.mapper

import eu.antoniolopez.playground.api.market.model.DTOValue
import eu.antoniolopez.playground.feature.dashboard.domain.model.Value
import io.reactivex.functions.Function

class ValueMapper : Function<List<DTOValue>, List<Value>> {
    override fun apply(list: List<DTOValue>): List<Value> =
        list.map {
            Value(
                date = it.date,
                value = it.value
            )
        }
}

package eu.antoniolopez.playground.feature.dashboard.domain.model

data class Market(
    val unit: String,
    val period: String,
    val values: List<Value>
)

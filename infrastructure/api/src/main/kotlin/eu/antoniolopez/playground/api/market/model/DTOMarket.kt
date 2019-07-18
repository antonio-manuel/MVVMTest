package eu.antoniolopez.playground.api.market.model

data class DTOMarket(
    val status: String,
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<DTOValue>
)

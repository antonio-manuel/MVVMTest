package eu.antoniolopez.playground.api.market.model

import com.squareup.moshi.Json
import java.util.*

data class DTOValue(
    @Json(name = "x")
    val date: Calendar,
    @Json(name = "y")
    val value: Double
)

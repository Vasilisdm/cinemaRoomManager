package org.example.cinemaroommanager.controllers.dto.stats

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class StatsResponse(
    @JsonProperty("current_income")
    val currentIncome: BigDecimal,
    @JsonProperty("number_of_available_seats")
    val numberOfAvailableSeats: Int,
    @JsonProperty("number_of_purchased_tickets")
    val numberOfPurchasedTickets: Int
)

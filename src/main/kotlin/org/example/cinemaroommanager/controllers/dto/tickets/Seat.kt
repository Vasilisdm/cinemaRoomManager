package org.example.cinemaroommanager.controllers.dto.tickets

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal


data class Seat(
    val row: Int,
    val column: Int,
    val price: BigDecimal
)

data class PurchasedSeat(
    val token: String,
    val row: Int,
    val column: Int,
    val price: BigDecimal
)

data class SeatResponse(
    @JsonProperty("total_rows")
    val totalRows: Int,
    @JsonProperty("total_columns")
    val totalColumns: Int,
    @JsonProperty("available_seats")
    val availableSeats: List<Seat>
)

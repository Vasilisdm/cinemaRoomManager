package org.example.cinemaroommanager.controllers.dto.tickets

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class PurchaseTicketRequest(
    val row: Int,
    val column: Int
)

data class PurchaseTicketResponse(
    val token: String,
    val ticket: Seat
)

data class RefundTicketRequest(
    val token: String
)

data class RefundTicketResponse(
    @JsonProperty("returned_ticket")
    val returnedTicket: ReturnedTicket
)

data class ReturnedTicket(
    val row: Int,
    val column: Int,
    val price: BigDecimal
)

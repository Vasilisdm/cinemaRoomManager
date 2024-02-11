package org.example.cinemaroommanager.controllers.exceptions.messages

const val SEAT_OUT_OF_BOUNDARIES = "The number of a row or a column is out of bounds!"
const val ALREADY_PURCHASED_TICKET = "The ticket has been already purchased!"
const val TOKEN_NOT_FOUND = "Wrong token!"

data class SeatOutOfBoundariesMessage(
    val error: String = SEAT_OUT_OF_BOUNDARIES
)

data class AlreadyPurchasedTicketMessage(
    val error: String = ALREADY_PURCHASED_TICKET
)

data class TokenNotFoundMessage(
    val error: String = TOKEN_NOT_FOUND
)

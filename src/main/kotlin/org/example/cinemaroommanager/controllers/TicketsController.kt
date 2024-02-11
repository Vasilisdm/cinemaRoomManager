package org.example.cinemaroommanager.controllers

import java.math.BigDecimal
import java.util.*
import org.example.cinemaroommanager.controllers.dto.stats.StatsResponse
import org.example.cinemaroommanager.controllers.dto.tickets.PurchaseTicketRequest
import org.example.cinemaroommanager.controllers.dto.tickets.PurchaseTicketResponse
import org.example.cinemaroommanager.controllers.dto.tickets.PurchasedSeat
import org.example.cinemaroommanager.controllers.dto.tickets.RefundTicketRequest
import org.example.cinemaroommanager.controllers.dto.tickets.RefundTicketResponse
import org.example.cinemaroommanager.controllers.dto.tickets.ReturnedTicket
import org.example.cinemaroommanager.controllers.dto.tickets.Seat
import org.example.cinemaroommanager.controllers.dto.tickets.SeatResponse
import org.example.cinemaroommanager.controllers.exceptions.PasswordNotProvidedOrIsWrongException
import org.example.cinemaroommanager.controllers.exceptions.SeatAlreadyPurchasedException
import org.example.cinemaroommanager.controllers.exceptions.SeatOutOfBoundariesException
import org.example.cinemaroommanager.controllers.exceptions.TokenNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

const val TOTAL_AVAILABLE_SEATS = 81

@RestController
class TicketsController {
    private val availableSeats = Collections.synchronizedList(ArrayList<Seat>())
    private val purchasedSeats = Collections.synchronizedList(ArrayList<PurchasedSeat>())

    init {
        for (i in 1..9) {
            for (j in 1..9) {
                if (i <= 4)
                    availableSeats.add(Seat(row = i, column = j, price = BigDecimal(10)))
                else
                    availableSeats.add(Seat(row = i, column = j, price = BigDecimal(8)))
            }
        }
    }


    @GetMapping(path = ["/seats"], produces = ["application/json"])
    fun seats(): ResponseEntity<SeatResponse> {
        val seatResponse = SeatResponse(
            totalRows = 9,
            totalColumns = 9,
            availableSeats = availableSeats.toList()
        )

        return ResponseEntity
            .ok()
            .body(seatResponse)
    }

    @PostMapping(path = ["/purchase"], produces = ["application/json"])
    fun purchaseTicket(@RequestBody request: PurchaseTicketRequest): ResponseEntity<PurchaseTicketResponse> {
        if (purchasedSeats.find { request.row == it.row && request.column == it.column } != null)
            throw SeatAlreadyPurchasedException()

        val customerSeat = availableSeats
            .find { it.row == request.row && it.column == request.column }
            ?: throw SeatOutOfBoundariesException()

        val token = UUID.randomUUID().toString()

        purchasedSeats.add(
            PurchasedSeat(
                token = token,
                row = customerSeat.row,
                column = customerSeat.column,
                price = customerSeat.price
            )
        )

        val purchaseTicketResponse = PurchaseTicketResponse(
            token = token,
            ticket = Seat(
                row = customerSeat.row,
                column = customerSeat.column,
                price = customerSeat.price
            )
        )

        return ResponseEntity
            .ok()
            .body(purchaseTicketResponse)
    }

    @PostMapping(path = ["/return"], produces = ["application/json"])
    fun refundTicket(@RequestBody request: RefundTicketRequest): ResponseEntity<RefundTicketResponse> {
        val refundableTicket = purchasedSeats.find { it.token == request.token }

        if (refundableTicket == null)
            throw TokenNotFoundException()

        purchasedSeats.remove(refundableTicket)

        val response = RefundTicketResponse(
            returnedTicket = ReturnedTicket(
                row = refundableTicket.row,
                column = refundableTicket.column,
                price = refundableTicket.price
            )
        )

        return ResponseEntity
            .ok()
            .body(response)
    }

    @GetMapping(path = ["/stats"], produces = ["application/json"])
    fun stats(@RequestParam("password", required =false) password:String?): ResponseEntity<StatsResponse> {
        if (password.isNullOrBlank() || password != "super_secret")
            throw PasswordNotProvidedOrIsWrongException()

        val response = StatsResponse(
            currentIncome = purchasedSeats.sumOf { it.price },
            numberOfAvailableSeats = TOTAL_AVAILABLE_SEATS - purchasedSeats.size,
            numberOfPurchasedTickets = purchasedSeats.size
        )

        return ResponseEntity
            .ok()
            .body(response)
    }
}
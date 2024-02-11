package org.example.cinemaroommanager.controllers.exceptions.handlers

import org.example.cinemaroommanager.controllers.exceptions.SeatAlreadyPurchasedException
import org.example.cinemaroommanager.controllers.exceptions.SeatOutOfBoundariesException
import org.example.cinemaroommanager.controllers.exceptions.TokenNotFoundException
import org.example.cinemaroommanager.controllers.exceptions.messages.AlreadyPurchasedTicketMessage
import org.example.cinemaroommanager.controllers.exceptions.messages.PasswordNotProvidedOrIsWrongMessage
import org.example.cinemaroommanager.controllers.exceptions.messages.SeatOutOfBoundariesMessage
import org.example.cinemaroommanager.controllers.exceptions.messages.TokenNotFoundMessage
import org.example.cinemaroommanager.controllers.exceptions.PasswordNotProvidedOrIsWrongException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class TicketControllerExceptionHandler {

    @ExceptionHandler(SeatOutOfBoundariesException::class)
    fun handleSeatOutOfBoundaries(
        e: SeatOutOfBoundariesException,
        request: WebRequest
    ): ResponseEntity<SeatOutOfBoundariesMessage> {
        val body = SeatOutOfBoundariesMessage()

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SeatAlreadyPurchasedException::class)
    fun handleSeatAlreadyPurchased(
        e: SeatAlreadyPurchasedException,
        request: WebRequest
    ): ResponseEntity<AlreadyPurchasedTicketMessage> {
        val body = AlreadyPurchasedTicketMessage()

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(TokenNotFoundException::class)
    fun handleTokenNotFound(
        e: TokenNotFoundException,
        request: WebRequest
    ): ResponseEntity<TokenNotFoundMessage> {
        val body = TokenNotFoundMessage()

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PasswordNotProvidedOrIsWrongException::class)
    fun passwordNotProvidedOrIsWrongException(
        e: PasswordNotProvidedOrIsWrongException,
        request: WebRequest
    ): ResponseEntity<PasswordNotProvidedOrIsWrongMessage> {
        val body = PasswordNotProvidedOrIsWrongMessage()

        return ResponseEntity(body, HttpStatus.UNAUTHORIZED)
    }
}
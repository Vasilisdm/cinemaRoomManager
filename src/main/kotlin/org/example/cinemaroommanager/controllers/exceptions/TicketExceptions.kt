package org.example.cinemaroommanager.controllers.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class SeatOutOfBoundariesException: RuntimeException()

@ResponseStatus(HttpStatus.BAD_REQUEST)
class SeatAlreadyPurchasedException: RuntimeException()

@ResponseStatus(HttpStatus.BAD_REQUEST)
class TokenNotFoundException: RuntimeException()

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class PasswordNotProvidedOrIsWrongException: RuntimeException()
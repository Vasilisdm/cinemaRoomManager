package org.example.cinemaroommanager.controllers.exceptions.messages

const val PASSWORD_NOT_PROVIDED_OR_IS_WRONG = "The password is wrong!"

data class PasswordNotProvidedOrIsWrongMessage(
    val error: String = PASSWORD_NOT_PROVIDED_OR_IS_WRONG
)

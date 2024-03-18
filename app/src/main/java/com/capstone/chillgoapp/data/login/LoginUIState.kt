package com.capstone.chillgoapp.data.login

data class LoginUIState(
    var email: String = "",
    var password: String = "",
    var message: String ="",

    var emailError: Boolean = false,
    var passwordError: Boolean = false
)

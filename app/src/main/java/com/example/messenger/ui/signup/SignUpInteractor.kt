package com.example.messenger.ui.signup

import com.example.messenger.ui.auth.AuthInteractor

interface SignUpInteractor : AuthInteractor {

    interface onSignUpFinishedListener {

        fun onSuccess()
        fun onUsernameError()
        fun onPasswordError()
        fun onPhoneNumberError()
        fun onError()
    }

    fun signUp(
        username: String,
        phoneNumber: String,
        password: String,
        listener: onSignUpFinishedListener
    )

    fun getAuthorization(listener: AuthInteractor.onAuthFinishedListener)
}
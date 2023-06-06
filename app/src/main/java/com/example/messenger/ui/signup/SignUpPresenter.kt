package com.example.messenger.ui.signup

import com.example.messenger.data.local.AppPreferences

interface SignUpPresenter {

    var preferences: AppPreferences
    fun executeSignUp(username: String, phoneNumber: String, password: String)
}
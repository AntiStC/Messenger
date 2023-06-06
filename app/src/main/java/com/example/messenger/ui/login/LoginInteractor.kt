package com.example.messenger.ui.login

import com.example.messenger.data.local.AppPreferences
import com.example.messenger.ui.auth.AuthInteractor

interface LoginInteractor : AuthInteractor {

    interface OnDetailsRetrievalFinishedListener {
        fun onDetailsRetrievalSuccess()
        fun onDetailsRetrievalError()
    }

    fun login(username: String, password: String, listener: AuthInteractor.onAuthFinishedListener)
    fun retrieveDetails(preferences: AppPreferences, listener: OnDetailsRetrievalFinishedListener)
}
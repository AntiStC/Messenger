package com.example.messenger.ui.auth

import com.example.messenger.data.local.AppPreferences
import com.example.messenger.data.vo.UserVO

interface AuthInteractor {

    val userDetails: UserVO
    val accessToken: String
    val submittedUsername: String
    val submittedPassword: String

    interface onAuthFinishedListener {

        fun onAuthSuccess()
        fun onAuthError()
        fun onUsernameError()
        fun onPasswordError()
    }

    fun persistAccessToken(preferences: AppPreferences)
    fun persistUserDetails(preferences: AppPreferences)
}
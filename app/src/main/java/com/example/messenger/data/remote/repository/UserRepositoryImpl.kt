package com.example.messenger.data.remote.repository

import android.content.Context
import com.example.messenger.data.local.AppPreferences
import com.example.messenger.data.vo.UserListVO
import com.example.messenger.data.vo.UserVO
import com.example.messenger.service.MessengerApiService
import io.reactivex.rxjava3.core.Observable

class UserRepositoryImpl(ctx: Context) : UserRepository {

    private val preferences: AppPreferences = AppPreferences.create(ctx)
    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun findById(id: Long): Observable<UserVO> {
        return service.showUser(id, preferences.accessToken as String)
    }

    override fun all(): Observable<UserListVO> {
        return service.listUsers(preferences.accessToken as String)
    }

    override fun echoDetails(): Observable<UserVO> {
        return service.echoDetails(preferences.accessToken as String)
    }
}
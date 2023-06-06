package com.example.messenger.data.remote.repository

import android.database.Observable
import com.example.messenger.data.vo.UserListVO
import com.example.messenger.data.vo.UserVO

interface UserRepository {

    fun findById(id: Long): io.reactivex.rxjava3.core.Observable<UserVO>
    fun all(): io.reactivex.rxjava3.core.Observable<UserListVO>
    fun echoDetails(): io.reactivex.rxjava3.core.Observable<UserVO>

}
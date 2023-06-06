package com.example.messenger.data.remote.repository

import android.database.Observable
import com.example.messenger.data.vo.ConversationListVO
import com.example.messenger.data.vo.ConversationVO

interface ConversationRepository {

    fun findConversationById(id: Long): io.reactivex.rxjava3.core.Observable<ConversationVO>
    fun all(): io.reactivex.rxjava3.core.Observable<ConversationListVO>
}
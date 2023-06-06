package com.example.messenger.ui.chat

import android.content.Context
import com.example.messenger.data.local.AppPreferences
import com.example.messenger.data.remote.repository.ConversationRepository
import com.example.messenger.data.remote.repository.ConversationRepositoryImpl
import com.example.messenger.data.remote.request.MessageRequestObject
import com.example.messenger.service.MessengerApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChatInteractorImpl(context: Context) : ChatInteractor {

    private val preferences: AppPreferences = AppPreferences.create(context)
    private val service: MessengerApiService = MessengerApiService.getInstance()
    private val conversationRepository: ConversationRepository = ConversationRepositoryImpl(context)

    override fun sendMessage(
        recipientId: Long,
        message: String,
        listener: ChatInteractor.onMessageSendFinishListener
    ) {
        service.createMessages(
            MessageRequestObject(recipientId, message),
            preferences.accessToken as String
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _ -> listener.onSendSuccess() },
                { error ->
                    listener.onSendError()
                    error.printStackTrace()
                })
    }

    override fun loadMessages(
        conversationId: Long,
        listener: ChatInteractor.onMessageLoadFinishListener
    ) {
        conversationRepository.findConversationById(conversationId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res -> listener.onLoadSuccess(res) },
                { error ->
                    listener.onLoadError()
                    error.printStackTrace()
                })
    }
}
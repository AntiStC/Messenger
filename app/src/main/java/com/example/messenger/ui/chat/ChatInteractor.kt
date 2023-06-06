package com.example.messenger.ui.chat

import com.example.messenger.data.vo.ConversationVO

interface ChatInteractor {

    interface onMessageSendFinishListener {

        fun onSendSuccess()

        fun onSendError()
    }

    interface onMessageLoadFinishListener {

        fun onLoadSuccess(conversationVO: ConversationVO)

        fun onLoadError()
    }

    fun sendMessage(recipientId: Long, message: String, listener: onMessageSendFinishListener)

    fun loadMessages(conversationId: Long, listener: onMessageLoadFinishListener)
}
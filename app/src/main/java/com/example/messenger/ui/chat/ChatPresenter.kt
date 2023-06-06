package com.example.messenger.ui.chat

interface ChatPresenter {

    fun sendMessage(recipientId: Long, message: String)
    fun loadMessage(conversationId: Long)
}
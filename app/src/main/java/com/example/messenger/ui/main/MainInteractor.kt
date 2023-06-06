package com.example.messenger.ui.main

import com.example.messenger.data.vo.ConversationListVO
import com.example.messenger.data.vo.UserListVO

interface MainInteractor {

    interface OnConversationsLoadFinishListener {
        fun onConversationsLoadSuccess(conversationsListVO: ConversationListVO)
        fun onConversationsLoadError()
    }

    interface OnContactsLoadFinishListener {
        fun onContactsLoadSuccess(userListVO: UserListVO)
        fun onContactsLoadError()
    }

    interface OnLogoutFinishListener {
        fun onLogoutSuccess()
    }

    fun loadContacts(listener: OnContactsLoadFinishListener)
    fun loadConversations(listener: OnConversationsLoadFinishListener)
    fun logout(listener: OnLogoutFinishListener)
}
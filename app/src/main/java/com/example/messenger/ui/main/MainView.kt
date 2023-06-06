package com.example.messenger.ui.main

import com.example.messenger.ui.base.BaseView

interface MainView:BaseView {
    fun showConversationLoadError()
    fun showContactsLoadError()
    fun showConversationScreen()
    fun showContactsScreen()
    fun getContactsFragment():MainActivity.ContactsFragment
    fun getConversationsFragment():MainActivity.ConversationsFragment
    fun showNoConversation()
    fun navigateToLogin()
    fun navigateToSettings()
}
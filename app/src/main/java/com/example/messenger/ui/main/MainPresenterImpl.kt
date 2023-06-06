package com.example.messenger.ui.main

import com.example.messenger.data.vo.ConversationListVO
import com.example.messenger.data.vo.UserListVO

class MainPresenterImpl(private val view: MainView) :
    MainPresenter,
    MainInteractor.OnConversationsLoadFinishListener,
    MainInteractor.OnContactsLoadFinishListener,
    MainInteractor.OnLogoutFinishListener {
    private val interactor = MainInteractorImpl(view.getContext())

    override fun onConversationsLoadSuccess(conversationsListVO: ConversationListVO) {
        if (!conversationsListVO.conversations.isEmpty()) {
            val conversationsFragment = view.getConversationsFragment()
            val conversations = conversationsFragment.conversations
            val adapter = conversationsFragment.conversationsAdapter

            conversations.clear()
            adapter.notifyDataSetChanged()
            conversationsListVO.conversations.forEach { contact ->
                conversations.add(contact)
                adapter.notifyItemInserted(conversations.size - 1)
            }
        } else {
            view.showNoConversation()
        }
    }

    override fun onConversationsLoadError() {
        view.showConversationLoadError()
    }

    override fun onContactsLoadSuccess(userListVO: UserListVO) {
        val contactFragment = view.getContactsFragment()
        val contacts = contactFragment.contacts
        val adapter = contactFragment.contactAdapter

        contacts.clear()
        adapter.notifyDataSetChanged()

        userListVO.users.forEach { contact ->
            contacts.add(contact)
            contactFragment.contactsAdapter.notifyItemInserepted(contacts.size - 1)
        }
    }

    override fun onContactsLoadError() {
        view.showContactsLoadError()
    }

    override fun onLogoutSuccess() {
        view.navigateToLogin()
    }

    override fun loadConversations() {
        interactor.loadConversations(this)
    }

    override fun loadContacts() {
        interactor.loadContacts(this)
    }

    override fun executeLogout() {
        interactor.logout(this)
    }
}
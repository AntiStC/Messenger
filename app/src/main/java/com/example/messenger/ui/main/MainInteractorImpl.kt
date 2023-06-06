package com.example.messenger.ui.main

import android.content.Context
import com.example.messenger.data.local.AppPreferences
import com.example.messenger.data.remote.repository.ConversationRepository
import com.example.messenger.data.remote.repository.ConversationRepositoryImpl
import com.example.messenger.data.remote.repository.UserRepository
import com.example.messenger.data.remote.repository.UserRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainInteractorImpl(val context: Context) : MainInteractor {

    private val userRepository: UserRepository = UserRepositoryImpl(context)
    private val conversationRepository: ConversationRepository = ConversationRepositoryImpl(context)

    override fun loadContacts(listener: MainInteractor.OnContactsLoadFinishListener) {

        userRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res -> listener.onContactsLoadSuccess(res) },
                { error ->
                    listener.onContactsLoadError()
                    error.printStackTrace()
                })
    }

    override fun loadConversations(listener: MainInteractor.OnConversationsLoadFinishListener) {

        conversationRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res -> listener.onConversationsLoadSuccess(res) },
                { error ->
                    listener.onConversationsLoadError()
                    error.printStackTrace()
                })
    }

    override fun logout(listener: MainInteractor.OnLogoutFinishListener) {
        val preferences: AppPreferences = AppPreferences.create(context)
        preferences.clear()
        listener.onLogoutSuccess()
    }
}
package com.example.messenger.ui.login

import com.example.messenger.data.local.AppPreferences
import com.example.messenger.ui.auth.AuthInteractor

class LoginPresenterImpl(private val view: LoginView) : LoginPresenter,
    AuthInteractor.onAuthFinishedListener, LoginInteractor.OnDetailsRetrievalFinishedListener {

    private val interactor: LoginInteractor = LoginInteractorImpl()
    private val preferences: AppPreferences = AppPreferences.create(view.getContext())

    override fun onPasswordError() {
        view.hideProgress()
        view.setPasswordError()
    }

    override fun onUsernameError() {
        view.hideProgress()
        view.setUsernameError()
    }

    override fun onAuthSuccess() {
        interactor.persistAccessToken(preferences)
        interactor.retrieveDetails(preferences, this)
    }

    override fun onAuthError() {
        view.showAuthError()
        view.hideProgress()
    }

    override fun onDetailsRetrievalSuccess() {
        interactor.persistUserDetails(preferences)
        view.hideProgress()
        view.navigateToHome()
    }

    override fun onDetailsRetrievalError() {
        interactor.retrieveDetails(preferences, this)
    }

    override fun executeLogin(username: String, password: String) {
        view.showProgress()
        interactor.login(username, password, this)
    }
}
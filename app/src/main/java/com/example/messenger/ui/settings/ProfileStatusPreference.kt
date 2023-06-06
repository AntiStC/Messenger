package com.example.messenger.ui.settings

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.EditText
import android.widget.Toast
import androidx.preference.PreferenceDialogFragmentCompat
import com.example.messenger.data.local.AppPreferences
import com.example.messenger.data.remote.request.StatusUpdateRequestObject
import com.example.messenger.service.MessengerApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileStatusPreference(context: Context, attributeSet: AttributeSet) :
    PreferenceDialogFragmentCompat() {

    private val service: MessengerApiService = MessengerApiService.getInstance()
    private val preferences: AppPreferences = AppPreferences.create(context)

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            val etStatus = EditText(context)

            if (TextUtils.isEmpty(etStatus.text)) {
                Toast.makeText(context, "Status cannot be empty.", Toast.LENGTH_LONG).show()
            } else {
                val requestObject = StatusUpdateRequestObject(etStatus.text.toString())

                service.updateUserStatus(requestObject, preferences.accessToken as String)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ res -> preferences.storeUserDetails(res) },
                        { error ->
                            Toast.makeText(
                                context,
                                "Unable to update status at the moment. Try again later.",
                                Toast.LENGTH_LONG
                            ).show()
                            error.printStackTrace()
                        })
            }
        }
    }
}
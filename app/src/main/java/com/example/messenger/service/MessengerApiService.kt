package com.example.messenger.service

import com.example.messenger.data.remote.request.LoginRequestObject
import com.example.messenger.data.remote.request.MessageRequestObject
import com.example.messenger.data.remote.request.StatusUpdateRequestObject
import com.example.messenger.data.remote.request.UserRequestObject
import com.example.messenger.data.vo.*
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MessengerApiService {

    @POST("login")
    @Headers("Content-Type: application/json")
    fun login(@Body user: LoginRequestObject): Observable<Response<ResponseBody>>

    @POST("users/registrations")
    fun createUser(@Body user: UserRequestObject): Observable<UserVO>

    @GET("users")
    fun listUsers(@Header("Authorization") authorization: String): Observable<UserListVO>

    @PUT("users")
    fun updateUserStatus(
        @Body request: StatusUpdateRequestObject,
        @Header("Authorization") authorization: String
    ): Observable<UserVO>

    @GET("users/{userId}")
    fun showUser(
        @Path("userId") userId: Long,
        @Header("Authorization") authorization: String
    ): Observable<UserVO>

    @GET("users/details")
    fun echoDetails(@Header("Authorization") authorization: String): Observable<UserVO>

    @POST("messages")
    fun createMessages(
        @Body messageRequestObject: MessageRequestObject,
        @Header("Authorization") authorization: String
    ): Observable<MessageVO>

    @GET("conversations")
    fun listConversations(@Header("Authorization") authorization: String):
            Observable<ConversationListVO>

    @GET("conversations/{conversationId}")
    fun showConversation(
        @Path("conversationId") conversationId: Long,
        @Header("Authorization") authorization: String
    ): Observable<ConversationVO>

    companion object Factory {

        private var service: MessengerApiService? = null

        fun getInstance(): MessengerApiService {
            if (service == null) {

                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://192.168.10.3:5000")
                    .build()

                service = retrofit.create(MessengerApiService::class.java)
            }
            return service as MessengerApiService
        }
    }
}
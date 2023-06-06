package com.example.messenger.ui.chat

import android.widget.Toast
import com.example.messenger.data.vo.ConversationVO
import com.example.messenger.utils.message.Message
import java.text.SimpleDateFormat

class ChatPresenterImpl(private val view: ChatView) : ChatPresenter,
    ChatInteractor.onMessageSendFinishListener, ChatInteractor.onMessageLoadFinishListener {

    private val interactor: ChatInteractor = ChatInteractorImpl(view.getContext())

    override fun onLoadSuccess(conversationVO: ConversationVO) {
        val adapter = view.getMessageListAdapter()
        val dataFormatter = SimpleDateFormat("yyyy-MM-dd- HH:mm:ss")

        conversationVO.messages.forEach { message ->
            adapter.addToStart(
                Message(
                    message.senderId,
                    message.body,
                    dataFormatter.parse(message.createdAt.split(".")[0])
                ), true
            )
        }
    }

    override fun onLoadError() {
        view.showConversationLoadError()
    }

    override fun onSendSuccess() {
        Toast.makeText(view.getContext(), "Message sent", Toast.LENGTH_LONG).show()
    }

    override fun onSendError() {
        view.showMessageSendError()
    }

    override fun sendMessage(recipientId: Long, message: String) {
        interactor.sendMessage(recipientId, message, this)
    }

    override fun loadMessage(conversationId: Long) {
        interactor.loadMessages(conversationId, this)
    }
}
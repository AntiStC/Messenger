package com.example.messenger.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.messenger.R
import com.example.messenger.data.local.AppPreferences
import com.example.messenger.utils.message.Message
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesList
import com.stfalcon.chatkit.messages.MessagesListAdapter
import java.util.*

class ChatActivity : AppCompatActivity(), ChatView, MessageInput.InputListener {

    private var recipientId: Long = -1
    private lateinit var messageList: MessagesList
    private lateinit var messageInput: MessageInput
    private lateinit var preferences: AppPreferences
    private lateinit var presenter: ChatPresenter
    private lateinit var messagesListAdapter: MessagesListAdapter<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("RECIPIENT_NAME")
        preferences = AppPreferences.create(this)
        messagesListAdapter = MessagesListAdapter(preferences.userDetails.id.toString(), null)
        presenter = ChatPresenterImpl(this)
        bindViews()

        val conversationId = intent.getLongExtra("CONVERSATION_ID", -1)
        recipientId = intent.getLongExtra("RECIPIENT_ID", -1)

        if (conversationId != -1L) presenter.loadMessage(conversationId)
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        messagesListAdapter.addToStart(
            Message(
                preferences.userDetails.id,
                input.toString(),
                Date()
            ), true
        )
        presenter.sendMessage(recipientId, input.toString())

        return true
    }

    override fun showConversationLoadError() {
        Toast.makeText(this, "Unable to load thread.Please try to again later.", Toast.LENGTH_LONG)
            .show()
    }

    override fun showMessageSendError() {
        Toast.makeText(this, "Unable to send message.Please try to again later.", Toast.LENGTH_LONG)
            .show()
    }

    override fun getContext(): Context {
        return this
    }

    override fun getMessageListAdapter(): MessagesListAdapter<Message> {
        return messagesListAdapter
    }

    override fun bindViews() {
        messageList = findViewById(R.id.messages_list)
        messageInput = findViewById(R.id.message_input)

        messageList.setAdapter(messagesListAdapter)
        messageInput.setInputListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressedDispatcher.onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
        finish()
    }
}
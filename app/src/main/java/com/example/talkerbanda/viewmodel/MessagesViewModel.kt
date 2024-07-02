package com.example.talkerbanda.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.talkerbanda.messages.Chat
import com.example.talkerbanda.messages.Message

class MessagesViewModel(application: Application) : AndroidViewModel(application) {

    private val _chats = MutableLiveData<List<Chat>>()
    val chats: LiveData<List<Chat>>
        get() = _chats

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>>
        get() = _messages

    init {
        _chats.value = listOf(
            Chat("1", "Анна", "Когда снова увидимся?", "https://avotar.ru/avatar/devushki/80/37.jpg"),
            Chat("2", "Борис", "Даллас проиграл 1-4!!!", "https://cdn.ebaumsworld.com/mediaFiles/picture/625755/80873131.jpg"),
            Chat("3", "Виктория", "Почему бросила занятия?", "https://avotar.ru/avatar/devushki/80/62.jpg")
        )
        _messages.value = mutableListOf()
    }

    fun addChat(chat: Chat) {
        val currentChats = _chats.value?.toMutableList() ?: mutableListOf()
        currentChats.add(chat)
        _chats.value = currentChats
    }

    fun removeChat(chat: Chat) {
        val currentChats = _chats.value?.toMutableList() ?: mutableListOf()
        currentChats.remove(chat)
        _chats.value = currentChats
    }

    fun sendMessage(messageText: String) {
        val newMessage = Message(
            id = "1",
            text = messageText,
            timestamp = System.currentTimeMillis().toString()
        )
        val currentMessages = _messages.value?.toMutableList() ?: mutableListOf()
        currentMessages.add(newMessage)
        _messages.value = currentMessages
    }

    fun searchMessages(query: String) {
        val currentMessages = _messages.value ?: return
        val filteredMessages = currentMessages.filter { message ->
            message.text.contains(query, ignoreCase = true)
        }
        _messages.value = filteredMessages
    }
}












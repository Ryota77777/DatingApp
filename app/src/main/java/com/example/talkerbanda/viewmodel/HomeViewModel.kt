package com.example.talkerbanda.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.talkerbanda.R

class HomeViewModel : ViewModel() {

    private val users = listOf(
        User(R.drawable.user3, "Анна,27\nМосква\nРаботаю графическим дизайнером. Люблю создавать красивые вещи и делиться творчеством."),
        User(R.drawable.user2, "Елена, 25\nСанкт-Петербург\nПрограммист. В свободное время люблю читать книги и путешествовать."),
        User(R.drawable.user1, "Мария, 23\nКиев\nФотограф. Стремлюсь запечатлеть красоту мира и передать её через объектив камеры."),
        User(R.drawable.user4, "Александра, 29\nНовосибирск\nПреподаватель. В свободное время увлекаюсь йогой и исследованием местной истории."),
        User(R.drawable.user5, "Дарья, 22\nРостов-на-Дону\nЖурналист. Люблю исследовать истории, которые меня окружают, и делиться этим с миром.")
    )

    private var currentIndex = 0

    val userDescription = ObservableField<String>()
    val userPhoto = ObservableField<Int>()

    init {
        loadInitialUser()
    }

    fun loadInitialUser() {
        userDescription.set(users[currentIndex].description)
        userPhoto.set(users[currentIndex].photo)
    }

    fun nextUser() {
        currentIndex = (currentIndex + 1) % users.size
        loadInitialUser() // При переходе к следующему пользователю загружаем его данные
    }
}

data class User(val photo: Int, val description: String)












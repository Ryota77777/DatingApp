package com.example.talkerbanda

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.talkerbanda.databinding.ActivityMainBinding
import com.example.talkerbanda.home.HomeFragment
import com.example.talkerbanda.messages.MessagesFragment
import com.example.talkerbanda.profile.ProfileFragment
import com.example.talkerbanda.search.SearchFragment
import com.example.talkerbanda.settings.SettingsFragment
import com.example.talkerbanda.auth.AuthActivity  // Импорт AuthActivity добавлен
import com.example.talkerbanda.viewmodel.HomeViewModel
import com.example.talkerbanda.viewmodel.MessagesViewModel
import com.example.talkerbanda.viewmodel.ProfileViewModel
import com.example.talkerbanda.viewmodel.SearchViewModel
import com.example.talkerbanda.viewmodel.SettingsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeViewModel: HomeViewModel by viewModels()
    private val messagesViewModel: MessagesViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_messages -> {
                    loadFragment(MessagesFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_search -> {
                    loadFragment(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    loadFragment(SettingsFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        // Проверяем статус аутентификации при запуске приложения
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            // Пользователь не авторизован, переходим на экран регистрации/входа
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        // Load default fragment
        if (savedInstanceState == null) {
            navView.selectedItemId = R.id.navigation_home
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
    }
}





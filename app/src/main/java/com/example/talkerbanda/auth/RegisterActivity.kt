package com.example.talkerbanda.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.talkerbanda.MainActivity
import com.example.talkerbanda.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val registerButton = binding.registerButton

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Регистрация пользователя в Firebase
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Регистрация успешна, переходим на главный экран
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        // Регистрация не удалась, обработайте ошибку здесь
                        Toast.makeText(baseContext, "Registration failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}


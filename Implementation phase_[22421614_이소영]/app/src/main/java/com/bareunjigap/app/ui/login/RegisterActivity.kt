package com.bareunjigap.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bareunjigap.app.databinding.ActivityRegisterBinding
import com.bareunjigap.app.data.repository.UserRepository
import com.bareunjigap.app.ui.dashboard.MainActivity
import com.bareunjigap.app.util.DailyNotificationWorker
import com.bareunjigap.app.util.SessionManager
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userRepo: UserRepository
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepo = UserRepository(this)
        session = SessionManager(this)

        binding.btnRegister.setOnClickListener { doRegister() }
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun doRegister() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(this, "비밀번호는 6자 이상이어야 합니다", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false

        lifecycleScope.launch {
            val result = userRepo.register(email, email, password)
            binding.progressBar.visibility = View.GONE
            binding.btnRegister.isEnabled = true

            result.onSuccess { user ->
                session.saveSession(user.userId, user.email, user.email)
                DailyNotificationWorker.scheduleDailyWork(this@RegisterActivity)
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finishAffinity()
            }
            result.onFailure { e ->
                Toast.makeText(this@RegisterActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

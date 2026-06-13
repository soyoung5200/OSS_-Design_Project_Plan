package com.bareunjigap.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bareunjigap.app.databinding.ActivityLoginBinding
import com.bareunjigap.app.data.repository.UserRepository
import com.bareunjigap.app.ui.dashboard.MainActivity
import com.bareunjigap.app.util.DailyNotificationWorker
import com.bareunjigap.app.util.SessionManager
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userRepo: UserRepository
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepo = UserRepository(this)
        session = SessionManager(this)

        // 이미 로그인 된 경우 바로 메인으로
        if (session.isLoggedIn()) {
            goToMain()
            return
        }

        binding.btnLogin.setOnClickListener { doLogin() }
        binding.btnGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun doLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "이메일과 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false

        lifecycleScope.launch {
            val user = userRepo.login(email, password)
            binding.progressBar.visibility = View.GONE
            binding.btnLogin.isEnabled = true

            if (user != null) {
                session.saveSession(user.userId, user.name, user.email)
                DailyNotificationWorker.scheduleDailyWork(this@LoginActivity)
                goToMain()
            } else {
                Toast.makeText(this@LoginActivity, "이메일 또는 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

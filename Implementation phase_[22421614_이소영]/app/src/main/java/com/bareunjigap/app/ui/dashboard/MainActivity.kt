package com.bareunjigap.app.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bareunjigap.app.R
import com.bareunjigap.app.databinding.ActivityMainBinding
import com.bareunjigap.app.ui.analysis.AnalysisFragment
import com.bareunjigap.app.ui.notification.NotificationFragment
import com.bareunjigap.app.ui.theme.ThemeFragment
import com.bareunjigap.app.ui.transaction.TransactionFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 기본 화면: 대시보드
        if (savedInstanceState == null) {
            replaceFragment(DashboardFragment())
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> replaceFragment(DashboardFragment())
                R.id.nav_transaction -> replaceFragment(TransactionFragment())
                R.id.nav_analysis -> replaceFragment(AnalysisFragment())
                R.id.nav_theme -> replaceFragment(ThemeFragment())
                R.id.nav_notification -> replaceFragment(NotificationFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}

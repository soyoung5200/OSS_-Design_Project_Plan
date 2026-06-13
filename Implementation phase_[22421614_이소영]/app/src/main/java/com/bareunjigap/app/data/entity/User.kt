package com.bareunjigap.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val email: String,
    val password: String, // SHA-256 해시 저장
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)

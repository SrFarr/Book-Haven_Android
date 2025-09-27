package com.example.bookhaven2.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user",
    indices = [
        Index(value = ["email"], unique = true)
    ]
)

data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val email:String,
    val username:String,
    val password:String,
    val isAdmin: Boolean = false
)
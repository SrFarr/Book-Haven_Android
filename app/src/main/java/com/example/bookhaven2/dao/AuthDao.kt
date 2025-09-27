package com.example.bookhaven2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookhaven2.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {
    @Insert
    suspend fun register(user: User)

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM user where email = :email AND password = :password")
    suspend fun login(email:String, password:String) : User?
}
package com.example.bookhaven2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bookhaven2.models.User

@Dao
interface UserDao {
    @Upsert
    suspend fun UpsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT COUNT(*) FROM user")
    suspend fun getUserCount(): Int
}
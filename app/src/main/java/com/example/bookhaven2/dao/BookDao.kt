package com.example.bookhaven2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bookhaven2.models.Book

@Dao
interface BookDao {
    @Upsert
    suspend fun upsertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT COUNT(*) FROM book")
    suspend fun getBookCount(): Int
}
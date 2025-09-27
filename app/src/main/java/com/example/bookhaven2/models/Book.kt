package com.example.bookhaven2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "book"
)

data class Book(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val author:String,
    val price:Double,
    val stock:Int,
    val category:String,
    val image:String,
    val createdAt:String
)

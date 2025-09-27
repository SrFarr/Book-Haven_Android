package com.example.bookhaven2.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transaction",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
        )
    ]
)

data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userId:Int,
    val totalPrice:Double,
    val createdAt:String
)

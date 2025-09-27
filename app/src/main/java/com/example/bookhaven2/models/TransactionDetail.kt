package com.example.bookhaven2.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactionDetail",
    foreignKeys = [
        ForeignKey(
            entity = Transaction::class,
            parentColumns = ["id"],
            childColumns = ["transactionId"],
        ),
        ForeignKey(
            entity = Book::class,
            parentColumns = ["id"],
            childColumns = ["bookId"]
        )
    ]
)

data class TransactionDetail(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val transactionId:Int,
    val bookId:Int,
    val qty:Int,
    val price:Int,
    val createdAt: String
)
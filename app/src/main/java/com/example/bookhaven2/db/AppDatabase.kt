package com.example.bookhaven2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bookhaven2.dao.AuthDao
import com.example.bookhaven2.models.Book
import com.example.bookhaven2.models.Transaction
import com.example.bookhaven2.models.TransactionDetail
import com.example.bookhaven2.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Book::class, Transaction::class, User::class, TransactionDetail::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "BookHavenDb"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = getDb(context).authDao()
                                dao.register(
                                    User(
                                        email = "farr@gmail.com",
                                        username = "farr",
                                        password = "123",
                                        isAdmin = true
                                    )
                                )
                                dao.register(
                                    User(
                                        email = "shir@gmail.com",
                                        username = "shir",
                                        password = "123",
                                        isAdmin = false
                                    )
                                )
                            }
                        }
                    })
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}

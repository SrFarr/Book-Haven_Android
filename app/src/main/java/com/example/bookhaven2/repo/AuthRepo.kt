package com.example.bookhaven2.repo

import com.example.bookhaven2.dao.AuthDao
import com.example.bookhaven2.models.User


class AuthRepo (private val authDao: AuthDao){

    suspend fun registUser(user: User) : Result<Unit>{
        val exist = authDao.getUserByEmail(user.email)
        return if(exist != null){
            Result.failure(Exception("Email sudah terdaftar"))
        } else{
            authDao.register(user)
            Result.success(Unit)
        }
    }
    suspend fun loginUser(email:String, password:String) : Result<User>{
        val user = authDao.login(email, password)
        return if(user != null){
            Result.success(user)
        }
        else{
            Result.failure(Exception("Akun tidak ditemukan"))
        }
    }
}
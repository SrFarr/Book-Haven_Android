package com.example.bookhaven2.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhaven2.db.AppDatabase
import com.example.bookhaven2.models.User
import com.example.bookhaven2.repo.AuthRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState{
    object Idle: AuthState()
    object Loading: AuthState()
    data class Success(val message: String, val  user: User?) : AuthState()
    data class Error(val error: String) : AuthState()
}

class AuthVm(application: Application) : AndroidViewModel(application) {

    private val repo: AuthRepo by lazy {
        val dao = AppDatabase.getDb(application).authDao()
        AuthRepo(dao)
    }

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(user: User) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val res = repo.registUser(user)
            _authState.value = res.fold(
                onSuccess = { AuthState.Success("Register Berhasil", null) },
                onFailure = { AuthState.Error("Register Gagal") }
            )
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repo.loginUser(email, password)
            _authState.value = result.fold(
                onSuccess = { user -> AuthState.Success("Login berhasil", user) },
                onFailure = { e -> AuthState.Error(e.message ?: "Login gagal") }
            )
        }
    }
}
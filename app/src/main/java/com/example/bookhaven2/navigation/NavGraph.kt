package com.example.bookhaven2.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookhaven2.ui.theme.BookHaven2Theme
import com.example.bookhaven2.ui.screen.AdminScreen.AdminDashboardUi
import com.example.bookhaven2.ui.screen.AuthScreen.LoginUi
import com.example.bookhaven2.ui.screen.AuthScreen.RegisterUI
import com.example.bookhaven2.ui.screen.UserScreen.UserDashboard

@Composable
fun NavGraph(navController: NavHostController) {
    var isDarkTheme by remember { mutableStateOf(false) }
    val systemDark = isSystemInDarkTheme()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login"){
            LoginUi(
                onLoginSuccess = { user ->
                    navController.navigate("homeUser"){
                        popUpTo("login"){
                            inclusive = true
                        }
                    }
                },
                onLoginAsAdmin = { user ->
                    navController.navigate("homeAdmin"){
                        popUpTo("login"){
                            inclusive = true
                        }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register"){
            RegisterUI(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.popBackStack()
                }
            )
        }

        composable("homeUser"){
            UserDashboard()
        }
        composable("homeAdmin") {
            AdminDashboardUi(
                isDarkTheme = isDarkTheme,
                onThemeChange = {
                    isDarkTheme = it
                }
            )
        }
    }
}
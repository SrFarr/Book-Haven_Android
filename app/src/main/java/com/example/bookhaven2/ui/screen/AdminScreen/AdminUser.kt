package com.example.bookhaven2.ui.screen.AdminScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookhaven2.ui.screen.AdminScreen.ui.theme.BookHaven2Theme
import com.example.bookhaven2.ui.screen.Komponen.AdminTopbarUi
import com.example.bookhaven2.ui.screen.Komponen.NavbarUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminUserUi(
    modif: Modifier = Modifier,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onLogoutClick:() -> Unit = {}
) {
    var expanded by remember { mutableStateOf(true) }
    val colors = MaterialTheme.colorScheme
    var name by remember { mutableStateOf("Farr") }
    var showDialog by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isAdmin by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                },
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            ) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        },
        topBar = {
            AdminTopbarUi(
                title = "Daftar Pengguna",
                icon = Icons.Default.Person,
                isDarkTheme = isDarkTheme,
                onThemeChange = {
                    onThemeChange(!isDarkTheme)
                },
                onLogoutClick = {
                    onLogoutClick()
                },
                expand = expanded
            )
        },
        bottomBar = {
            NavbarUi(index = 1)
        }
    ) { paddingValues ->
        Column(modifier = modif
            .padding(paddingValues)
            .padding(16.dp))
        {
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text("Tambah Pengguna")
                    },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                label = { Text("Email") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            OutlinedTextField(
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Username") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(Modifier.height(16.dp))

                            Text("Role:", style = MaterialTheme.typography.titleMedium)

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = isAdmin,
                                    onClick = { isAdmin = true }
                                )
                                Text("Admin")
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = !isAdmin,
                                    onClick = { isAdmin = false }
                                )
                                Text("User")
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            showDialog = false
                            println("User ditambah: $username, role=${if (isAdmin) "Admin" else "User"}")
                        }) {
                            Text("Simpan")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Batal")
                        }
                    }
                )
            }

            CardUser(name = name, colors = colors)
        }
    }
}

@Composable
fun CardUser(modif: Modifier = Modifier, name:String, colors: ColorScheme) {
    Card(
        modifier = modif
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary
        )
    )
    {
        Row (modifier = modif
            .fillMaxWidth()
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Row (modifier = modif
                .fillMaxWidth()
                .weight(1f), verticalAlignment = Alignment.CenterVertically){
                Box(
                    modifier = modif
                        .clip(
                            shape = CircleShape
                        )
                        .background(colors.onSurface)
                        .width(32.dp)
                        .height(32.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(Icons.Default.Person, null)
                }
                Spacer(modifier = modif.width(8.dp))
                Text(name, style = MaterialTheme.typography.titleLarge)
            }
            Row (modifier = modif
                .fillMaxWidth()
                .weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(Icons.Default.Delete, "Btn Delete")
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(Icons.Default.Edit, "Btn Edit")
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun Prev() {
    var isDarkTheme by remember { mutableStateOf(false) }

    val systemDark = isSystemInDarkTheme()

    if(!isDarkTheme && systemDark){
        isDarkTheme = true
    }
    BookHaven2Theme (
        darkTheme = isDarkTheme
    ){
        AdminUserUi(isDarkTheme = isDarkTheme,  onThemeChange = {
            isDarkTheme = it
        })
    }
}
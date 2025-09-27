package com.example.bookhaven2.ui.screen.Komponen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookhaven2.ui.theme.BookHaven2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminTopbarUi(
    modif: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    isDarkTheme: Boolean,
    onLogoutClick: () -> Unit,
    onThemeChange: (Boolean) -> Unit,
    expand: Boolean
) {
    var expanded by remember { mutableStateOf(expand) }
    val colors = MaterialTheme.colorScheme
    TopAppBar(
        title = {
            Row (modifier = modif.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                Row(modifier = modif.weight(1.5f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                    Icon(icon, null)
                    Spacer(modif.width(16.dp))
                    Text(title)
                }
                Row (modifier = modif.weight(1f), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically){
                    IconButton(
                        onClick = {
                            expanded = !expanded
                        }
                    ) {
                        Icon(Icons.Default.MoreVert, null)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                onLogoutClick()
                            },
                            leadingIcon = {
                                Icon(Icons.AutoMirrored.Filled.Logout, null)
                            },
                            text = {
                                Text("Logout")
                            },
                        )

                        DropdownMenuItem(
                            onClick = {
                                onThemeChange(!isDarkTheme)
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = if(isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                                    contentDescription = null
                                )
                            },
                            text = { Text(if (isDarkTheme) "Light Mode" else "Dark Mode") }
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colors.primary,
            titleContentColor = colors.onPrimary
        )
    )
}

package com.example.bookhaven2.ui.screen.Komponen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookhaven2.ui.theme.BookHaven2Theme

@Composable
fun NavbarUi(modif: Modifier = Modifier, index: Int = 0) {
    var selectedIndex by remember { mutableIntStateOf(index) }
    val colors = MaterialTheme.colorScheme
    val items = listOf(
        "Dashboard",
        "User",
        "Book",
        "Transaction"
    )

    val selectedIcon = listOf(
        Icons.Filled.Home,
        Icons.Filled.Person,
        Icons.AutoMirrored.Filled.LibraryBooks,
        Icons.Filled.Money
    )

    val unselectedIcon = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Person,
        Icons.AutoMirrored.Outlined.LibraryBooks,
        Icons.Outlined.Money
    )

    NavigationBar (
        modif.fillMaxWidth(),
        containerColor = colors.primary
    ){
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                },
                icon = {
                    Icon(
                        imageVector = if(selectedIndex == index) selectedIcon[index] else unselectedIcon[index],
                        null
                    )
                },
                label = {
                    Text(item)
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.onPrimary,
                    unselectedIconColor = colors.onSurfaceVariant,
                    selectedTextColor = colors.onPrimary,
                    unselectedTextColor = colors.onSurfaceVariant,
                    indicatorColor = colors.primaryContainer
                )
            )
        }
    }
}

@Preview
@Composable
private fun Prev() {
    BookHaven2Theme {
        NavbarUi()
    }
}
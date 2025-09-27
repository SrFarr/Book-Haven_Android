package com.example.bookhaven2.ui.screen.AdminScreen


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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookhaven2.ui.screen.Komponen.AdminTopbarUi
import com.example.bookhaven2.ui.screen.Komponen.NavbarUi
import com.example.bookhaven2.ui.theme.BookHaven2Theme


data class DashboardStats(
    val title:String,
    val value:String,
    val icon: @Composable () -> Unit,
    val backgroundColor: Color
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardUi(
    modif: Modifier = Modifier,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onLogoutClick:() -> Unit = {})
{
    var selectedIndex by remember { mutableIntStateOf(0) }
    val colors = MaterialTheme.colorScheme
    var expanded by remember { mutableStateOf(true) }

    val stats = listOf(
        DashboardStats(
            title = "Buku",
            value = "17",
            icon = {
                Icon(
                    Icons.AutoMirrored.Filled.LibraryBooks,
                    contentDescription = "Books",
                    tint = colors.onPrimary
                )
            },
            backgroundColor = colors.primary
        ),
        DashboardStats(
            title = "Pelanggan",
            value = "17",
            icon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Users",
                    tint = colors.onPrimary
                )
            },
            backgroundColor = colors.primary
        )
    )
    Scaffold(
        topBar = {
            AdminTopbarUi(
                title = "Admin Dashboard",
                icon = Icons.Default.Home,
                isDarkTheme = isDarkTheme,
                onLogoutClick = { onLogoutClick() },
                onThemeChange = {newTheme ->
                    onThemeChange(newTheme)
                },
                expand = expanded,
            )
        },
        bottomBar = {
            NavbarUi()
        }
    ) { paddingValues ->
        Column(modif
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp))
        {
            Text(
                "Dashboard overview",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground,
                modifier = modif.padding(bottom = 16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modif.padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(stats) { stat ->
                    StatCard(stat = stat)
                }
            }
            StatCard(
                stat = DashboardStats(
                    title = "Total Transaksi",
                    value = "17",
                    icon = {
                        Icon(
                            Icons.Filled.Money,
                            contentDescription = "Transactions",
                            tint = colors.onPrimary
                        )
                    },
                    backgroundColor = colors.primary
                )
            )
            Spacer(modifier = modif.height(16.dp))
            Text(
                "Recent Activity",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.onBackground,
                modifier = modif.padding(vertical = 16.dp)
            )

            Card(
                modifier = modif
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surfaceVariant
                )
            ) {
                Box(
                    modifier = modif.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Aktivitas terbaru akan ditampilkan di sini",
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AdminPrev() {
    var isDarkTheme by remember { mutableStateOf(false) }
    val systemDark = isSystemInDarkTheme()

    if(!isDarkTheme && systemDark){
        isDarkTheme = true
    }
    BookHaven2Theme(
        darkTheme = isDarkTheme,
    ) {
        AdminDashboardUi(
            isDarkTheme = isDarkTheme,
            onThemeChange = {
                isDarkTheme = it
            }
        )
    }
}


@Composable
fun StatCard(stat: DashboardStats) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = stat.backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                stat.icon()
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stat.title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stat.value,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
        }
    }
}


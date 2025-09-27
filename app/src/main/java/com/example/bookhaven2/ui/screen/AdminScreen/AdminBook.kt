package com.example.bookhaven2.ui.screen.AdminScreen

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.bookhaven2.models.Book
import com.example.bookhaven2.ui.screen.AdminScreen.ui.theme.BookHaven2Theme
import com.example.bookhaven2.ui.screen.Komponen.AdminTopbarUi
import com.example.bookhaven2.ui.screen.Komponen.NavbarUi
import java.io.File
import java.io.FileOutputStream

fun saveImageToFolder(context: Context, uri: Uri) : String?{
    return try{
        val folder = File(context.filesDir, "Images")
        if(!folder.exists()) folder.mkdirs()

        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(folder, "img_${System.currentTimeMillis()}.jpg")

        val os = FileOutputStream(file)
        inputStream?.copyTo(os)
        inputStream?.close()
        os.close()

        file.absolutePath
    } catch (e: Exception){
        e.printStackTrace()
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminBookUi(
    modif: Modifier = Modifier,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onLogoutClick:() -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme
    var showDialog by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

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
                title = "Daftar Buku",
                icon = Icons.Default.Book,
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
                    Text("Tambah Buku Baru")
                },
                text = {
                    var expanded by remember { mutableStateOf(false) }
                    val categories = listOf(
                        "Fiction", "Non-Fiction", "Science", "Technology",
                        "History", "Biography", "Fantasy", "Romance",
                        "Mystery", "Children", "Education", "Business"
                    )
                    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
                    var savePath by remember { mutableStateOf<String?>(null) }

                    val context = LocalContext.current
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent()
                    ) { uri:Uri? ->
                        uri?.let {
                            selectedImageUri = it
                            savePath = saveImageToFolder(context, it)
                        }
                    }

                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = { },
                            label = { Text("Judul Buku") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = "",
                            onValueChange = { },
                            label = { Text("Penulis") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                label = { Text("Harga") },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                            )

                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                label = { Text("Stok") },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        var expanded by remember { mutableStateOf(false) }
                        val categories = listOf(
                            "Fiction", "Non-Fiction", "Science", "Technology",
                            "History", "Biography", "Fantasy", "Romance",
                            "Mystery", "Children", "Education", "Business"
                        )

                        Box(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                label = { Text("Kategori") },
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                                trailingIcon = {
                                    Icon(Icons.Default.ArrowDropDown, null)
                                }
                            )
                            Spacer(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(Color.Transparent)
                                    .clickable { expanded = true }
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category) },
                                    onClick = {
                                        expanded = false
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                launcher.launch("image/*")
                            }
                        ) {
                            Text("Pilih Gambar")
                        }

                        selectedImageUri?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Preview:")
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp)
                            )
                        }

                        savePath?.let{
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Disimpan di: $it")
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Simpan")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showDialog = false },
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("Batal")
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
        }

            CardBook(
                book = Book(
                    id = 1,
                    title = "Harry Potter",
                    author = "J.K. Rowling",
                    price = 29.99,
                    stock = 50,
                    category = "Fantasy",
                    image = "",
                    createdAt = "2024-01-01"
                ),
                colors = colors,
                onEditClick = { /* Handle edit */ },
                onDeleteClick = { /* Handle delete */ }
            )

        }
    }
}

@Composable
fun CardBook(
    modifier: Modifier = Modifier,
    book: Book,
    colors: ColorScheme,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    if (book.image.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(colors.onSurface)
                                .size(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Book,
                                contentDescription = null,
                                tint = colors.surface
                            )
                        }
                    } else {
                        AsyncImage(
                            model = book.image,
                            contentDescription = book.title,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(48.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = book.author,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onDeleteClick) {
                        Icon(Icons.Default.Delete, "Delete Book")
                    }
                    IconButton(onClick = onEditClick) {
                        Icon(Icons.Default.Edit, "Edit Book")
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Price: $${book.price}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Stock: ${book.stock}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Text(
                    text = book.category,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .background(
                            color = colors.secondaryContainer,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Tanggal dibuat
            Text(
                text = "Created: ${book.createdAt}",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun BookPrev() {
    var isDarkTheme by remember { mutableStateOf(false) }

    val systemDark = isSystemInDarkTheme()

    if(!isDarkTheme && systemDark){
        isDarkTheme = true
    }
    BookHaven2Theme (
        darkTheme = isDarkTheme
    ){
        AdminBookUi(isDarkTheme = isDarkTheme,  onThemeChange = {
            isDarkTheme = it
        })
    }
}
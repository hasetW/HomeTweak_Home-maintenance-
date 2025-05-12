package com.example.myapplication.ui.customer


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.OffWhite
import com.example.myapplication.viewmodel.UserViewModel
import androidx.compose.material3.Button as Button1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    userId: Int = 1, // Pass the actual userId if you have it
    viewModel: UserViewModel
) {
    var firstName by remember { mutableStateOf("John") }
    var email by remember { mutableStateOf("john.doe@example.com") }
    var password by remember { mutableStateOf("********") }

    val context = LocalContext.current

    Scaffold(
        containerColor = OffWhite,
        topBar = {
            TopAppBar(
                title = { Text("Account", color = DarkBlue) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = OffWhite)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Full Name (Optional): $firstName", color = DarkBlue)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Email: $email", color = DarkBlue)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Password: $password", color = DarkBlue)
            Spacer(modifier = Modifier.height(16.dp))

            Button1(
                onClick = {
                    viewModel.logoutUser {
                        Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
                        // Navigate to login screen if needed
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = OffWhite)
            ) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout", modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Logout")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button1(
                onClick = {
                    viewModel.deleteAccount(
                        userId,
                        onSuccess = {
                            Toast.makeText(context, "Account deleted", Toast.LENGTH_SHORT).show()
                            // Navigate to signup/login screen
                        },
                        onError = { error ->
                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                        }
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.8f), contentColor = OffWhite)
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Account", modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Delete Account")
            }
        }
    }
}

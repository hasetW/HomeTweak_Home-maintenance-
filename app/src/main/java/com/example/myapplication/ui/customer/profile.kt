package com.example.myapplication.ui.customer

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.OffWhite
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.SafetyOrange
import com.example.myapplication.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    userId: Int,
    viewModel: ProfileViewModel = viewModel()
) {
    val context = LocalContext.current
    val user by viewModel.user.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val successMessage by viewModel.successMessage.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchUserById(userId)
    }

    Scaffold(
        containerColor = OffWhite,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account Icon",
                            tint = DarkBlue,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Account", color = DarkBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = OffWhite)
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = CardColor,
                contentPadding = PaddingValues(horizontal = 30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier
                        .weight(1f)
                        .size(35.dp),
                    tint = SafetyOrange
                )
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Bookings",
                    modifier = Modifier
                        .weight(1f)
                        .size(35.dp),
                    tint = SafetyOrange
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .weight(1f)
                        .size(35.dp),
                    tint = SafetyOrange
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            user?.let {
                Text(text = "Full Name: ${it.username}", color = DarkBlue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email: ${it.email}", color = DarkBlue)
                Spacer(modifier = Modifier.height(16.dp))
            } ?: run {
                Text("Loading user info...", color = DarkBlue)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = {
                    viewModel.logout()
                    Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
                    // TODO: Navigate to login screen
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor = OffWhite)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Logout",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Logout")
            }

            Spacer(modifier = Modifier.height(8.dp))

            successMessage?.let {
                LaunchedEffect(it) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            errorMessage?.let {
                LaunchedEffect(it) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

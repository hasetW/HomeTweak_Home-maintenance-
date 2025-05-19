package com.example.myapplication.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.remote.RegisterRequest
import com.example.myapplication.viewmodel.SignUpViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSignInClick: () -> Unit,
    onSignUpSuccess: () -> Unit,
    viewModel: SignUpViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val roleOptions = listOf("customer", "serviceProvider")
    var selectedRole by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9F5)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.home_tweak),
                    contentDescription = "Logo",
                    modifier = Modifier.height(150.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Sign Up",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color(0xFF04285E),
                        fontSize = 36.sp
                    ),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Create new account",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color(0xFF4F5255)
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Email field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Enter your email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color(0xFFFFF1E8)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Username field
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = { Text("Enter your username") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color(0xFFFFF1E8)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Enter your password") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color(0xFFFFF1E8)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Role dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedRole,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        placeholder = { Text("Select your role") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(4.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFFFF1E8)
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color(0xFFFFF1E8))
                    ) {
                        roleOptions.forEach { role ->
                            DropdownMenuItem(
                                text = { Text(role) },
                                onClick = {
                                    selectedRole = role
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (email.isBlank() || username.isBlank() || password.isBlank() || selectedRole.isBlank()) {
                                errorMessage = "Please fill all fields"
                                successMessage = null
                                return@launch
                            }

                            val registerRequest = RegisterRequest(
                                email = email,
                                username = username,
                                password = password,
                                role = selectedRole
                            )

                            val result = viewModel.register(registerRequest)
                            result.onSuccess {
                                successMessage = "Registration successful"
                                errorMessage = null
                                onSignUpSuccess()
                            }.onFailure {
                                errorMessage = it.localizedMessage ?: "Registration failed"
                                successMessage = null
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B00)),
                    shape = RoundedCornerShape(40),
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .height(65.dp)
                ) {
                    Text("Sign Up", fontSize = 28.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                errorMessage?.let { Text(it, color = Color.Red) }
                successMessage?.let { Text(it, color = Color.Green) }

                Spacer(modifier = Modifier.height(24.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Already have an account?", fontSize = 16.sp, color = Color(0xFF4F5255))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Sign in",
                        fontSize = 16.sp,
                        color = Color(0xFFFF6B00),
                        modifier = Modifier.clickable { onSignInClick() }
                    )
                }
            }
        }
    }
}

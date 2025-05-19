package com.example.myapplication.ui.common

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.utils.AuthManager
import com.example.myapplication.viewmodel.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.remote.LoginRequest
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF9F5)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.home_tweak),
                    contentDescription = "Logo",
                    modifier = Modifier.height(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Login",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color(0xFF04285E),
                        fontSize = 36.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Sign in to your account via email",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF4F5255))
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Email Input
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Enter your email", style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF4F5255))) },
                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon", tint = Color(0xFF4F5255)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color(0xFFFFF1E8)
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Password Input
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Enter your password", style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF4F5255))) },
                    leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon", tint = Color(0xFF4F5255)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color(0xFFFFF1E8)
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                uncheckedColor = Color(0xFF999999),
                                checkmarkColor = Color.White
                            )
                        )
                        Text(
                            "Remember me",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF4F5255),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Text(
                        text = "Forgot password?",
                        color = Color(0xFF4F5255),
                        fontSize = 18.sp,
                        modifier = Modifier.clickable { onForgotPasswordClick() }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = {
                        errorMessage = null
                        isLoading = true
                        coroutineScope.launch {
                            val loginRequest = LoginRequest(
                                email = email,
                                password = password
                            )
                            val result = viewModel.login(loginRequest)
                            isLoading = false

                            result.onSuccess { loginResponse ->
                                AuthManager.token = loginResponse.token
                                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                                // TODO: Navigate to your home screen here
                            }.onFailure { error ->
                                errorMessage = error.localizedMessage ?: "Login failed"
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B00)),
                    shape = RoundedCornerShape(40),
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .height(65.dp),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Text("Login", fontSize = 28.sp, color = Color.White)
                    }
                }


                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    Text(
                        "Not a member?",
                        style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF4F5255), fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Create new account",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFFFF6B00),
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { onSignUpClick() }
                    )
                }
            }
        }
    }
}

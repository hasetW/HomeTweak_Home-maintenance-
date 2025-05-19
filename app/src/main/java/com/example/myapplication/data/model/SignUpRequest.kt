package com.example.myapplication.data.model

data class SignUpRequest(
    val email: String,
    val password: String,
    val username: String,
    val role: String
)


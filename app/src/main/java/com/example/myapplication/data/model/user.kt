package com.example.myapplication.data.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val password: String
)
enum class UserRole(val value: String) {
    CUSTOMER("customer"),
    PROVIDER("provider")
}

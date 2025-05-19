package com.example.myapplication.data.repository

import com.example.myapplication.data.model.User
import com.example.myapplication.data.remote.LoginRequest
import com.example.myapplication.data.remote.LoginResponse
import com.example.myapplication.data.remote.RegisterRequest
import com.example.myapplication.data.remote.UserService
import retrofit2.Call

class UserRepository(private val userService: UserService) {

    fun registerUser(request: RegisterRequest): Call<LoginResponse> = userService.register(request)

    fun loginUser(request: LoginRequest): Call<LoginResponse> = userService.login(request)

    fun accessAsProvider(token: String): Call<Map<String, String>> = userService.accessAsProvider("Bearer $token")

    fun accessAsCustomer(token: String): Call<Map<String, String>> = userService.accessAsCustomer("Bearer $token")

    fun deleteUser(userId: Int): Call<Void> = userService.deleteUser(userId)

    fun getUserById(userId: Int): Call<User> = userService.getUserById(userId)
}


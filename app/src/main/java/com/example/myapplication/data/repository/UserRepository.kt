package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.LoginRequest
import com.example.myapplication.data.remote.LoginResponse
import com.example.myapplication.data.remote.RegisterRequest
import com.example.myapplication.data.remote.UserService
import retrofit2.Call

class UserRepository(private val userService: UserService) {

    fun registerUser(request: RegisterRequest): Call<Void> {
        return userService.register(request)
    }

    fun loginUser(request: LoginRequest): Call<LoginResponse> {
        return userService.login(request)
    }

    fun accessAsProvider(token: String): Call<Map<String, String>> {
        return userService.accessAsProvider("Bearer $token")
    }

    fun accessAsCustomer(token: String): Call<Map<String, String>> {
        return userService.accessAsCustomer("Bearer $token")
    }

    fun deleteUser(userId: Int): Call<Void> {
        return userService.deleteUser(userId)
    }
}

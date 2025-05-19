package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.remote.LoginRequest
import com.example.myapplication.data.remote.LoginResponse
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Sending login request: $request")
                val response = repository.loginUser(request).awaitResponse()
                Log.d(TAG, "Response received: ${response.code()}")

                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "Login successful: ${response.body()}")
                    Result.success(response.body()!!)
                } else {
                    Log.e(TAG, "Login failed: ${response.code()} ${response.errorBody()?.string()}")
                    Result.failure(Exception("Login failed: ${response.code()}"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception during login", e)
                Result.failure(e)
            }
        }
    }
}


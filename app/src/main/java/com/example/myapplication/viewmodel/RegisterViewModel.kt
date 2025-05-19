package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.remote.LoginResponse
import com.example.myapplication.data.remote.RegisterRequest
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class SignUpViewModel(private val repository: UserRepository) : ViewModel() {

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    suspend fun register(request: RegisterRequest): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = repository.registerUser(request).awaitResponse()
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Log.d(TAG, "Registration & login success: $loginResponse")
                        Result.success(loginResponse)
                    } else {
                        Result.failure(Exception("Empty response"))
                    }
                } else {
                    Log.e(TAG, "Registration failed: ${response.code()} - ${response.errorBody()?.string()}")
                    Result.failure(Exception("Registration failed: ${response.code()}"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception during registration", e)
                Result.failure(e)
            }
        }
    }

}

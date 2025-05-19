package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage

    fun fetchUserById(userId: Int) {
        Log.d("UserViewModel", "Attempting to fetch user with ID: $userId")
        repository.getUserById(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val fetchedUser = response.body()
                    _user.value = fetchedUser
                    Log.d("UserViewModel", "User fetched: $fetchedUser")
                } else {
                    _errorMessage.value = "User not found"
                    Log.w("UserViewModel", "Failed to fetch user with ID: $userId. Response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _errorMessage.value = "Network error: ${t.localizedMessage}"
                Log.e("UserViewModel", "Network failure while fetching user with ID: $userId", t)
            }
        })
    }



    fun logout() {
        Log.i("UserViewModel", "User logged out")
        _user.value = null
        _successMessage.value = "Logged out"
        // TODO: Also clear token and user ID from SharedPreferences or DataStore here
    }
}

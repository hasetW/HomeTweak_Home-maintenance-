package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // You may store login state using SharedPreferences or DataStore in real apps
    fun deleteAccount(userId: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(userId).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        onSuccess()
                    } else {
                        onError("Failed to delete account: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    onError("Error: ${t.localizedMessage}")
                }
            })
        }
    }

    fun logoutUser(onLogout: () -> Unit) {

        onLogout()
    }
}

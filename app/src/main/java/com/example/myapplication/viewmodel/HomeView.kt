package com.example.myapplication.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.Booking
import com.example.myapplication.data.model.Provider
import com.example.myapplication.data.model.Category
import com.example.myapplication.data.repository.BookingRepository
import com.example.myapplication.data.repository.ProviderRepository
import com.example.myapplication.data.repository.CategoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageViewModel(
    private val providerRepository: ProviderRepository,
    private val categoryRepository: CategoryRepository,
    private val bookingRepository: BookingRepository
) : ViewModel() {

    var filteredProviders = mutableStateOf<List<Provider>>(emptyList())
        private set

    var categories = mutableStateOf<List<Category>>(emptyList())
        private set

    var bookingSuccess = mutableStateOf(false)
    var bookingError = mutableStateOf<String?>(null)


    fun createBooking(booking: Booking) {
        bookingRepository.createBooking(booking).enqueue(object : Callback<Booking> {
            override fun onResponse(call: Call<Booking>, response: Response<Booking>) {
                if (response.isSuccessful) {
                    Log.d("BookingSuccess", "Booking created: ${response.body()}")
                } else {
                    Log.e("BookingError", "Error: ${response.code()}, ${response.errorBody()?.string()}")
                }
            }
            override fun onFailure(call: Call<Booking>, t: Throwable) {
                Log.e("BookingFailure", "Failure: ${t.message}")
            }
        })
    }


    fun getAllCategories() {
        categoryRepository.getCategories().enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    categories.value = response.body() ?: emptyList()
                } else {
                    Log.e("CategoryError", "Response error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Log.e("CategoryError", "Failed to load categories", t)
            }
        })
    }

    fun getAllProviders() {
        providerRepository.getAllProviders().enqueue(object : Callback<List<Provider>> {
            override fun onResponse(call: Call<List<Provider>>, response: Response<List<Provider>>) {
                if (response.isSuccessful) {
                    filteredProviders.value = response.body() ?: emptyList()
                } else {
                    Log.e("ProviderError", "Response error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Provider>>, t: Throwable) {
                Log.e("ProviderError", "Failed to load providers", t)
            }
        })
    }

    fun filterByCategory(categoryId: Int) {
        if (categoryId == 0) {
            getAllProviders()
        } else {
            providerRepository.getProvidersByCategory(categoryId).enqueue(object : Callback<List<Provider>> {
                override fun onResponse(call: Call<List<Provider>>, response: Response<List<Provider>>) {
                    if (response.isSuccessful) {
                        filteredProviders.value = response.body() ?: emptyList()
                    } else {
                        Log.e("ProviderError", "Response error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<List<Provider>>, t: Throwable) {
                    Log.e("ProviderError", "Failed to load providers", t)
                }
            })
        }
    }

    fun getCategoryNameById(categoryId: Int): String {
        return categories.value.firstOrNull { it.id == categoryId }?.name ?: "Unknown"
    }
}

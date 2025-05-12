package com.example.myapplication.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.Provider
import com.example.myapplication.data.model.Category // Import Category model
import com.example.myapplication.data.repository.ProviderRepository
import com.example.myapplication.data.repository.CategoryRepository // Import CategoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageViewModel(
    private val providerRepository: ProviderRepository,
    private val categoryRepository: CategoryRepository // Add CategoryRepository to fetch categories
) : ViewModel() {

    // Holds the filtered list of providers
    var filteredProviders = mutableStateOf<List<Provider>>(emptyList())
        private set

    // Holds the list of categories
    var categories = mutableStateOf<List<Category>>(emptyList())
        private set

    // Fetch all categories
    fun getAllCategories() {
        categoryRepository.getCategories().enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    Log.d("CategorySuccess", "Categories: ${response.body()}")
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

    // Function to fetch all providers from the repository
    fun getAllProviders() {
        providerRepository.getAllProviders().enqueue(object : Callback<List<Provider>> {
            override fun onResponse(call: Call<List<Provider>>, response: Response<List<Provider>>) {
                if (response.isSuccessful) {
                    Log.d("ProviderSuccess", "Providers: ${response.body()}")
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

    // Function to fetch providers by category
    fun filterByCategory(categoryId: Int) {
        if (categoryId == 0) {
            getAllProviders() // Show all providers
        } else {
            providerRepository.getProvidersByCategory(categoryId).enqueue(object : Callback<List<Provider>> {
                override fun onResponse(call: Call<List<Provider>>, response: Response<List<Provider>>) {
                    if (response.isSuccessful) {
                        Log.d("ProviderSuccess", "Providers: ${response.body()}")
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

    // Function to get category name by categoryId
    fun getCategoryNameById(categoryId: Int): String {
        return categories.value.firstOrNull { it.id == categoryId }?.name ?: "Unknown"
    }
}

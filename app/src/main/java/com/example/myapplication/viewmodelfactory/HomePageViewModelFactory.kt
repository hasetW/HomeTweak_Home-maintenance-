package com.example.myapplication.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repository.BookingRepository
import com.example.myapplication.data.repository.CategoryRepository
import com.example.myapplication.data.repository.ProviderRepository
import com.example.myapplication.viewmodel.HomePageViewModel

class HomePageViewModelFactory(
    private val providerRepository: ProviderRepository,
    private val categoryRepository: CategoryRepository,
    private val bookingRepository: BookingRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomePageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomePageViewModel(providerRepository, categoryRepository, bookingRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

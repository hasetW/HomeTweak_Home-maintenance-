package com.example.myapplication.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repository.BookingRepository
import com.example.myapplication.viewmodel.CustomerBookingViewModel

class CustomerBookingViewModelFactory(
    private val bookingRepository: BookingRepository,
    private val customerId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerBookingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CustomerBookingViewModel(bookingRepository, customerId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

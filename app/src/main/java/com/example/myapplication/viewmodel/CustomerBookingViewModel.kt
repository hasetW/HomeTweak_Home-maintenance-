package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Booking
import com.example.myapplication.data.model.BookingStatus
import com.example.myapplication.data.repository.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class CustomerBookingViewModel(
    private val bookingRepository: BookingRepository,
    private val customerId: Int // This should come from login session or token decoding
) : ViewModel() {

    private val _currentTab = MutableStateFlow(BookingStatus.ACTIVE)
    val currentTab: StateFlow<BookingStatus> = _currentTab

    private val _allBookings = MutableStateFlow<List<Booking>>(emptyList())
    private val _displayedBookings = MutableStateFlow<List<Booking>>(emptyList())
    val displayedBookings: StateFlow<List<Booking>> = _displayedBookings

    init {
        fetchBookings()
    }

    fun onTabSelected(status: BookingStatus) {
        _currentTab.value = status
        filterBookingsByStatus(status)
    }

    fun cancelBooking(bookingId: Int) {
        viewModelScope.launch {
            val response = bookingRepository
                .updateBookingStatus(bookingId, BookingStatus.DECLINED)
                .awaitResponse()

            if (response.isSuccessful) {
                fetchBookings()
            }
        }
    }

    private fun fetchBookings() {
        viewModelScope.launch {
            val response = bookingRepository
                .getBookingsByUserId(customerId)
                .awaitResponse()

            if (response.isSuccessful) {
                val bookings = response.body() ?: emptyList()
                _allBookings.value = bookings
                filterBookingsByStatus(_currentTab.value)
            }
        }
    }

    private fun filterBookingsByStatus(status: BookingStatus) {
        _displayedBookings.value = _allBookings.value.filter { it.status == status.name }

    }
}

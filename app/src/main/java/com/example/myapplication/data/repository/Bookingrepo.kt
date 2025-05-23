package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Booking
import com.example.myapplication.data.remote.BookingService
import com.example.myapplication.data.model.BookingStatus
import com.example.myapplication.data.model.BookingStatusUpdateRequest


import retrofit2.Call

class BookingRepository(private val bookingService: BookingService) {

    fun createBooking(booking: Booking): Call<Booking> {
        return bookingService.createBooking(booking)
    }

    fun getBookingsByUserId(userId: Int): Call<List<Booking>> {
        return bookingService.getBookingsByUserId(userId)
    }

    fun getBookingsByProviderId(providerId: Int): Call<List<Booking>> {
        return bookingService.getBookingsByProviderId(providerId)
    }

    fun updateBookingStatus(bookingId: Int, request: BookingStatusUpdateRequest): Call<Booking> {
        return bookingService.updateBookingStatus(bookingId, request)
    }
    fun getBookingById(bookingId: Int): Call<Booking> {
        return bookingService.getBookingById(bookingId)
    }
}

package com.example.myapplication.data.model
data class Booking(
    val id: Int,
    val userId: Int,
    val providerId: Int,
    val serviceDate: String,
    val bookingDate: String,
    val status: String,
    val serviceType: String,
)
enum class BookingStatus {
    PENDING,
    COMPLETED,
    DECLINED,
    ACTIVE
}

package com.example.myapplication.data.model
data class Booking(
    val id: Int? = null, // Optional
    val userId: Int,
    val providerId: Int,
    val serviceDate: String,
    val bookingDate: String,
    val status: String = "PENDING",
    val providerName: String? = null,
    val hourlyRate: String? = null,
    val location: String? = null,
    val categoryName: String? = null,
)

enum class BookingStatus {
    PENDING,
    COMPLETED,
    CANCELED,
    ACTIVE
}
data class BookingStatusUpdateRequest(
    val status: String
)


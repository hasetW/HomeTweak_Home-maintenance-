package com.example.myapplication.ui.reusables


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.Booking

@Composable
fun CompletedBookingCard(
    booking: Booking
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Service: ${booking.serviceType}")
            Text("Completed on: ${booking.bookingDate}")
            Text("Thanks for using our service!")
        }
    }
}

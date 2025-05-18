package com.example.myapplication.ui.reusables//package com.example.myapplication.ui.reusables


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.Booking
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.MutedGrey

@Composable
fun CompletedBookingCard(
    booking: Booking
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = CardColor,
            contentColor = MutedGrey)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Completed on: ${booking.bookingDate}")
            Text("Thanks for using our service!")
        }
    }
}

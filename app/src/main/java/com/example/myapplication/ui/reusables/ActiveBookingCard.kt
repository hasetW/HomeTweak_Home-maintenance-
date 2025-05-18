package com.example.myapplication.ui.reusables//package com.example.myapplication.ui.reusables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.Booking
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.MutedGrey

@Composable
fun ActiveBookingCard(
    booking: Booking,
    onRescheduleClick: () -> Unit,
    onCancelClick: () -> Unit
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
            Text("Provider ID: ${booking.providerId}")
            Text("Time: ${booking.bookingDate}")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onRescheduleClick) {
                    Text("Reschedule")
                }
                TextButton(onClick = onCancelClick) {
                    Text("Cancel")
                }
            }
        }
    }
}

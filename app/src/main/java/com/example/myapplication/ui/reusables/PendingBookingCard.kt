package com.example.myapplication.ui.reusables//package com.example.myapplication.ui.reusables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.model.Booking
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.MutedGrey
import com.example.myapplication.ui.theme.SafetyOrange

@Composable
fun PendingBookingCard(
    booking: Booking,
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
            Text("Service: ${booking.categoryName}",
                color = DarkBlue,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Service Provider: ${booking.providerName}",
                color = MutedGrey,
                fontSize = 15.sp
            )
            Text("Date: ${booking.bookingDate}",
                color = MutedGrey,
                fontSize = 15.sp
            )
            Text("Location: ${booking.location}",
                color = MutedGrey,
                fontSize = 15.sp
            )
            Text("Status: ${booking.status}",
                color = MutedGrey,
                fontSize = 15.sp
            )
            Text("Price: ${booking.hourlyRate}/birr",
                color = MutedGrey,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(onClick ={},
                    colors = ButtonDefaults.buttonColors(SafetyOrange)) {
                    Text("Reschedule")
                }
                TextButton(onClick = onCancelClick,
                        colors = ButtonDefaults.buttonColors(MutedGrey)) {
                    Text("Decline")
                }
            }
        }
    }
}

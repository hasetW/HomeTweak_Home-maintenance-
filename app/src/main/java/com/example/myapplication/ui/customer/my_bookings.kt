package com.example.myapplication.ui.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.BookingStatus
import com.example.myapplication.ui.reusables.CompletedBookingCard
import com.example.myapplication.ui.reusables.PendingBookingCard
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.SafetyOrange
import com.example.myapplication.viewmodel.CustomerBookingViewModel

@Composable
fun CustomerBookingsScreen(viewModel: CustomerBookingViewModel = viewModel()) {
    val currentTab by viewModel.currentTab.collectAsState()
    val displayedBookings by viewModel.displayedBookings.collectAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = CardColor,
                contentPadding = PaddingValues(horizontal = 30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier
                        .weight(1f)
                        .size(35.dp),
                    tint = SafetyOrange
                )
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Bookings",
                    modifier = Modifier
                        .weight(1f)
                        .size(35.dp),
                    tint = SafetyOrange
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .weight(1f)
                        .size(35.dp),
                    tint = SafetyOrange
                )
            }
        },
        containerColor = Color(0xFFFFF9F5)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp, vertical = 18.dp)
                .background(Color(0xFFFFF9F5)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My Bookings",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 30.sp),
                    color = DarkBlue,
                    modifier = Modifier.padding(30.dp)
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Icon",
                    tint = SafetyOrange,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9F5)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TabItem(
                    title = "Pending",
                    isSelected = currentTab == BookingStatus.PENDING,
                    onTabClick = { viewModel.onTabSelected(BookingStatus.PENDING) }
                )
                TabItem(
                    title = "Completed",
                    isSelected = currentTab == BookingStatus.COMPLETED,
                    onTabClick = { viewModel.onTabSelected(BookingStatus.COMPLETED) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(displayedBookings) { booking ->
                    val bookingStatus = try {
                        BookingStatus.valueOf(booking.status)
                    } catch (e: IllegalArgumentException) {
                        null
                    }

                    when (bookingStatus) {
                        BookingStatus.PENDING -> PendingBookingCard(
                            booking = booking,
                            onCancelClick = { booking.id?.let { viewModel.cancelBooking(it) } }
                        )
                        BookingStatus.COMPLETED -> CompletedBookingCard(
                            booking = booking
                        )
                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun TabItem(
    title: String,
    isSelected: Boolean,
    onTabClick: () -> Unit
) {
    Button(
        onClick = onTabClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (isSelected) Color.Black else Color.Gray
        ),
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .height(48.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = if (isSelected) Color.Black else Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(30.dp)
                    .background(
                        if (isSelected) SafetyOrange else Color.Transparent,
                        shape = RoundedCornerShape(1.dp)
                    )
            )
        }
    }
}

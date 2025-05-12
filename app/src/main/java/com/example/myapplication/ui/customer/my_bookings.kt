//package com.example.myapplication.ui.customer
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.myapplication.data.model.BookingStatus
//import com.example.myapplication.ui.reusables.ActiveBookingCard
//import com.example.myapplication.ui.reusables.CompletedBookingCard
//import com.example.myapplication.ui.reusables.PendingBookingCard
//import com.example.myapplication.viewmodels.CustomerBookingViewModel
//
//@Composable
//fun CustomerBookingsScreen(viewModel: CustomerBookingViewModel = viewModel()) {
//    val currentTab by viewModel.currentTab.collectAsState()
//    val displayedBookings by viewModel.displayedBookings.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp, vertical = 18.dp)
//            .background(Color(0xFFFFF9F5)),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 14.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "My Bookings",
//                style = MaterialTheme.typography.titleMedium.copy(fontSize = 30.sp),
//                color = Color(0xFF04285E),
//                modifier = Modifier.padding(30.dp)
//            )
//            Icon(Icons.Default.Person, contentDescription = "Profile Icon")
//        }
//
//        Spacer(modifier = Modifier.height(2.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFFFFF9F5)),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            TabItem(
//                title = "Active",
//                isSelected = currentTab == BookingStatus.ACTIVE,
//                onTabClick = { viewModel.onTabSelected(BookingStatus.ACTIVE) }
//            )
//            TabItem(
//                title = "Pending",
//                isSelected = currentTab == BookingStatus.PENDING,
//                onTabClick = { viewModel.onTabSelected(BookingStatus.PENDING) }
//            )
//            TabItem(
//                title = "Completed",
//                isSelected = currentTab == BookingStatus.COMPLETED,
//                onTabClick = { viewModel.onTabSelected(BookingStatus.COMPLETED) }
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        LazyColumn {
//            items(displayedBookings) { booking ->
//                val bookingStatus = try {
//                    BookingStatus.valueOf(booking.status)
//                } catch (e: IllegalArgumentException) {
//                    null
//                }
//
//                when (bookingStatus) {
//                    BookingStatus.ACTIVE -> ActiveBookingCard (
//                        booking = booking,
//                        onRescheduleClick = { /* handle reschedule */ },
//                        onCancelClick = { viewModel.cancelBooking(booking.id) }
//                    )
//
//                    BookingStatus.PENDING -> PendingBookingCard(
//                        booking = booking,
//                        onCancelClick = { viewModel.cancelBooking(booking.id) }
//                    )
//
//                    BookingStatus.COMPLETED -> CompletedBookingCard(
//                        booking = booking
//                    )
//
//                    else -> {}
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TabItem(
//    title: String,
//    isSelected: Boolean,
//    onTabClick: () -> Unit
//) {
//    val backgroundColor = if (isSelected) Color(0xFF04285E) else Color.LightGray
//    val textColor = if (isSelected) Color.White else Color.Black
//    Button(
//        onClick = onTabClick,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = backgroundColor,
//            contentColor = textColor
//        ),
//        modifier = Modifier
//            .padding(horizontal = 4.dp)
//            .height(40.dp)
//    ) {
//        Text(text = title)
//    }
//}

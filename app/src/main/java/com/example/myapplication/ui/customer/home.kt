package com.example.myapplication.ui.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.MutedGrey
import com.example.myapplication.ui.theme.OffWhite
import com.example.myapplication.ui.theme.SafetyOrange
import com.example.myapplication.viewmodel.HomePageViewModel
import com.example.myapplication.data.model.Provider
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.model.Booking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Category(val name: String, val img: Int, val id: Int)

@Composable
fun HomePage(viewModel: HomePageViewModel) {
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    val categoryList = listOf(
        Category("Plumber", R.drawable.plumber, 1),
        Category("Painter", R.drawable.painter, 2),
        Category("Carpenter", R.drawable.carpenter, 3),
        Category("Electrician", R.drawable.electrician, 4),
        Category("Contractor", R.drawable.subcontractor, 5),
        Category("Gardner", R.drawable.gardner, 6)
    )

    val filteredProviders = viewModel.filteredProviders.value

    LaunchedEffect(Unit) {
        viewModel.getAllProviders()
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = CardColor,
                contentPadding = PaddingValues(horizontal = 30.dp)
            ) {
                Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.weight(1f).size(35.dp), tint = SafetyOrange)
                Icon(Icons.Default.CalendarToday, contentDescription = "Bookings", modifier = Modifier.weight(1f).size(35.dp), tint = SafetyOrange)
                Icon(Icons.Default.Person, contentDescription = "Profile", modifier = Modifier.weight(1f).size(35.dp), tint = SafetyOrange)
            }
        },
        containerColor = OffWhite
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                placeholder = { Text("Search for services") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Text(
                        text = "Categories",
                        color = DarkBlue,
                        style = MaterialTheme.typography.displayLarge
                    )
                }

                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        items(categoryList) { category ->
                            CustomLazyCard(category = category, onClick = {
                                viewModel.filterByCategory(category.id)
                            })
                        }
                    }
                }

                item {
                    Text(
                        text = "Top Rated",
                        style = MaterialTheme.typography.displayLarge,
                        color = DarkBlue
                    )
                }

                items(filteredProviders) { provider ->
                    LazyColumnCard(provider = provider, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun LazyColumnCard(provider: Provider, viewModel: HomePageViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(CardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    ProviderImage(provider.imageUrl)

                    Column {
                        Text(
                            text = " ${provider.username} ",
                            style = MaterialTheme.typography.titleLarge,
                            color = DarkBlue
                        )

                        Text(
                            text = " ${provider.category}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MutedGrey
                        )
                        Text(
                            text = "Rating: ${provider.rating}/5",
                            color = DarkBlue
                        )
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = SafetyOrange,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { expanded = !expanded }
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Years of Experience: ${provider.yearsOfExperience} Years",
                    color = MutedGrey,
                    fontSize = 16.sp
                )
                Text(
                    text = "Phone No: ${provider.phoneNumber}",
                    color = MutedGrey,
                    fontSize = 16.sp
                )
                Text(
                    text = "Price: \$${provider.hourlyRate}/hr",
                    color = DarkBlue,
                    fontSize = 16.sp
                )
                Text(
                    text = "Location: ${provider.location}",
                    color = DarkBlue,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                    Button(
                        onClick = {
                            val currentDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(Date())

                            val booking = Booking(
                                userId = 1, // replace with actual userId
                                providerId = provider.id,
                                serviceDate = currentDate,
                                bookingDate = currentDate,
                                status = "PENDING"
                            )


                            viewModel.createBooking(booking)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SafetyOrange,
                            contentColor = OffWhite,
                        )
                    ) {
                        Text("Book")
                    }


                    Button(onClick = { expanded = false },
                        colors = ButtonDefaults.buttonColors(MutedGrey)
                    ) {
                        Text(text = "Cancel")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomLazyCard(category: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(35.dp))
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = category.img),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = category.name,
                color = MutedGrey,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ProviderImage(imageUrl: String?) {
    Image(
        painter = if (!imageUrl.isNullOrBlank()) {
            rememberAsyncImagePainter(model = imageUrl)
        } else {
            painterResource(R.drawable.gardner)
        },
        contentDescription = "Provider Image",
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(50.dp))
    )
}



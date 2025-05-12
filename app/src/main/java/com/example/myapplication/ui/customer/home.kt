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
import com.example.myapplication.R
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.MutedGrey
import com.example.myapplication.ui.theme.OffWhite
import com.example.myapplication.ui.theme.SafetyOrange
import com.example.myapplication.viewmodel.HomePageViewModel
import   com.example.myapplication.data.model.Provider

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

    // Directly observe filteredProviders in the composable
    val filteredProviders = viewModel.filteredProviders.value

    // Fetch providers on first load
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

            Text(
                text = "Categories",
                color = DarkBlue,
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                items(categoryList) { category ->
                    CustomLazyCard(category = category, onClick = {
                        viewModel.filterByCategory(category.id)
                    })
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Top Rated",
                style = MaterialTheme.typography.displayLarge,
                color = DarkBlue
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(filteredProviders) { provider ->
                    LazyColumnCard(provider = provider, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun LazyColumnCard(provider: Provider, viewModel: HomePageViewModel) {
    // Fetch category name by categoryId
    val categoryName = viewModel.getCategoryNameById(provider.categoryId)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(CardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.painter),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
            Column {
                Text(
                    text = provider.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = DarkBlue
                )
                Text(
                    text = categoryName, // Use the category name here
                    style = MaterialTheme.typography.bodyLarge,
                    color = MutedGrey
                )
                Text(
                    text = "Rating: ${provider.rating}/5",
                    color = DarkBlue
                )
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


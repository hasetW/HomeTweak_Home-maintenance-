package com.example.myapplication.ui.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.CardColor
import com.example.myapplication.ui.theme.DarkBlue
import com.example.myapplication.ui.theme.MutedGrey
import com.example.myapplication.ui.theme.OffWhite

data class CategoryItem(val name: String, val imgRes: Int)

val categories = listOf(
    CategoryItem("Plumber", R.drawable.plumber),
    CategoryItem("Painter", R.drawable.painter),
    CategoryItem("Electrician", R.drawable.electrician),
    CategoryItem("Contractor", R.drawable.subcontractor),
    CategoryItem("Gardner", R.drawable.gardner),
    CategoryItem("Carpenter", R.drawable.carpenter)
)

@Composable
fun CategoryPage( navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.displayLarge,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Choose Your Service",
                style = MaterialTheme.typography.bodyLarge,
                color = MutedGrey
            )
            Text(
                text = ">>",
                color = DarkBlue
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            itemsIndexed(categories.chunked(2)) { _, rowItems ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    rowItems.forEach { item ->
                        CustomCard(name = item.name, img = item.imgRes)
                    }
                    if (rowItems.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun CustomCard(
    name: String,
    img: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .height(150.dp)
            .clickable { },
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
                painter = painterResource(id = img),
                contentDescription = name,
                modifier = Modifier
                    .width(85.dp)
                    .height(85.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = MutedGrey,
                textAlign = TextAlign.Center
            )
        }
    }
}

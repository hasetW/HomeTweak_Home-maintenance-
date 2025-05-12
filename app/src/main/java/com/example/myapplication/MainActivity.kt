package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.data.repository.*
import com.example.myapplication.ui.customer.HomePage
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.HomePageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val providerRepository = ProviderRepository(RetrofitClient.providerService)
        val categoryRepository = CategoryRepository(RetrofitClient.categoryService)

        val viewModel = HomePageViewModel(providerRepository, categoryRepository)

        setContent {
            MyApplicationTheme {
                HomePage(viewModel=viewModel)
            }
        }
    }
}

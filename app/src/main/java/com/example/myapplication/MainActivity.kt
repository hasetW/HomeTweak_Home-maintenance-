package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.data.repository.*
import com.example.myapplication.ui.common.LoginScreen
import com.example.myapplication.ui.common.SignUpScreen
import com.example.myapplication.ui.customer.AccountScreen
import com.example.myapplication.ui.customer.CategoryPage
import com.example.myapplication.ui.customer.CustomerBookingsScreen
import com.example.myapplication.ui.customer.HomePage
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.CustomerBookingViewModel
import com.example.myapplication.viewmodel.HomePageViewModel
import com.example.myapplication.viewmodel.LoginViewModel
import com.example.myapplication.viewmodel.SignUpViewModel
import com.example.myapplication.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.init(applicationContext)

        val providerRepository = ProviderRepository(RetrofitClient.providerService)
        val categoryRepository = CategoryRepository(RetrofitClient.categoryService)
        val bookingRepository = BookingRepository(RetrofitClient.bookingService)
        val userRepository = UserRepository(RetrofitClient.userService)

        // val viewModel = HomePageViewModel(providerRepository, categoryRepository, bookingRepository)
        // val viewModel = CustomerBookingViewModel(bookingRepository, customerId = 1)
        // val viewModel = UserViewModel(userRepository)
        val viewModel = LoginViewModel(userRepository)
        // val viewModel = SignUpViewModel(userRepository)

        setContent {
            MyApplicationTheme {
                // HomePage(viewModel = viewModel)
                // ServicePage()
                // CustomerBookingsScreen(viewModel = viewModel)
                // AccountScreen(userId = 3, viewModel = viewModel)
                // CategoryPage()
                LoginScreen(
                    onSignUpClick = { /* Handle sign up click */ },
                    onForgotPasswordClick = { /* Handle forgot password click */ },
                    viewModel = viewModel
                )

                // SignUpScreen(
                //     onSignInClick = {},
                //     viewModel = viewModel
                // )
            }
        }
    }
}

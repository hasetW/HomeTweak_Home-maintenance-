package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.repository.BookingRepository
import com.example.myapplication.data.repository.CategoryRepository
import com.example.myapplication.data.repository.ProviderRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.common.LandingPage
import com.example.myapplication.ui.common.SignUpScreen
import com.example.myapplication.ui.customer.CategoryPage
import com.example.myapplication.viewmodel.SignUpViewModel
import com.example.myapplication.viewmodelfactory.SignUpViewModelFactory

@Composable
fun AppNavGraph(
    userRepository: UserRepository,
    providerRepository: ProviderRepository,
    categoryRepository: CategoryRepository,
    bookingRepository: BookingRepository,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") {
            LandingPage(onGetStartedClick = {
                navController.navigate("signup")
            })
        }

        composable("signup") {
            val signUpFactory = SignUpViewModelFactory(userRepository)
            val signUpViewModel: SignUpViewModel = viewModel(factory =signUpFactory)

            SignUpScreen(
                viewModel = signUpViewModel,
                onSignInClick = { navController.navigate("landing") },
                onSignUpSuccess = {
                    navController.navigate("category") {
                        popUpTo("signup") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable("category") {
            CategoryPage(navController = navController)
        }
    }
}

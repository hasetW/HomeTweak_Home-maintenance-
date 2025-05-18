package com.example.myapplication.ui.provider//@file:OptIn(ExperimentalMaterial3Api::class)
//package com.example.myapplication.ui.provider
//
//
//
//
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.myapplication.ui.reusables.SignUpSectionCard
//import com.example.myapplication.ui.theme.MutedGrey
//import com.example.myapplication.ui.theme.OffWhite
//import com.example.myapplication.viewmodel.FinishSignUpViewModel
//
//@Composable
//fun SignUpScreen(viewModel: FinishSignUpViewModel = viewModel()) {
//    val state by viewModel.uiState.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxHeight()
//                            .padding(start = 20.dp)
//                    ) {
//                        Text(
//                            text = "Finish Signing Up",
//                            style = MaterialTheme.typography.titleLarge
//                        )
//                    }
//                },
//                modifier = Modifier
//                    .padding(top = 0.dp)
//                    .height(70.dp)
//                    .shadow(5.dp),
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = OffWhite),
//            )
//        }
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(horizontal = 16.dp, vertical = 30.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            item {
//                val basicFields: List<Triple<String, String, (String) -> Unit>> = listOf(
//                    Triple("Full Name", state.name) { viewModel.onValueChanged("name", it) },
//
//                    Triple("Phone Number", state.phoneNumber) { viewModel.onValueChanged("phoneNumber", it) },
//                    Triple("Location", state.location) { viewModel.onValueChanged("location", it) },
//                    Triple("Image URL", state.imageUrl) { viewModel.onValueChanged("imageUrl", it) }
//                )
//                SignUpSectionCard(
//                    title = "Basic Information",
//                    expanded = state.basicExpanded,
//                    onExpandToggle = { viewModel.toggleSection("basic") },
//                    fields = basicFields
//                )
//            }
//
//            item {
//                val professionalFields: List<Triple<String, String, (String) -> Unit>> = listOf(
//                    Triple("Hourly Rate", state.hourlyRate) { viewModel.onValueChanged("hourlyRate", it) },
//                    Triple("Years of Experience", state.yearsOfExperience.toString()) {
//                        viewModel.onValueChanged("yearsOfExperience", it)
//                    },
//                    Triple("Rating", state.rating.toString()) { viewModel.onValueChanged("rating", it) },
//                    Triple("Category ID", state.categoryId.toString()) { viewModel.onValueChanged("categoryId", it) },
//                    Triple("User ID", state.userId.toString()) { viewModel.onValueChanged("userId", it) }
//                )
//                SignUpSectionCard(
//                    title = "Professional Details",
//                    expanded = state.professionalExpanded,
//                    onExpandToggle = { viewModel.toggleSection("professional") },
//                    fields = professionalFields
//                )
//            }
//
//
//            item {
//                val serviceFields: List<Triple<String, String, (String) -> Unit>> = listOf(
//                    Triple("Service Type", state.servicetype) { viewModel.onValueChanged("serviceType", it) }
//                )
//                SignUpSectionCard(
//                    title = "Services Provided",
//                    expanded = state.servicesExpanded,
//                    onExpandToggle = { viewModel.toggleSection("services") },
//                    fields = serviceFields
//                )
//            }
//
//            item {
//                val certFields: List<Triple<String, String, (String) -> Unit>> = listOf(
//                    Triple("Certificate", state.certificate) { viewModel.onValueChanged("certificate", it) }
//                )
//                SignUpSectionCard(
//                    title = "Certifications / Licenses",
//                    expanded = state.certificationsExpanded,
//                    onExpandToggle = { viewModel.toggleSection("certifications") },
//                    fields = certFields
//                )
//            }
//
//            item {
//                Button(
//                    onClick = {
//                        viewModel.createProvider { success ->
//                            // Handle result (toast or nav)
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 24.dp)
//                ) {
//                    Text("Save changes")
//                }
//            }
//
//            item {
//                TextButton(
//                    onClick = { viewModel.logout() },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 16.dp)
//                ) {
//                    Text("Logout", color = MutedGrey)
//                }
//            }
//        }
//    }
//}
package com.example.myapplication.viewmodel//package com.example.myapplication.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.myapplication.data.model.Provider
//import com.example.myapplication.data.repository.ProviderRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//// Match UI state to the Provider model
//data class FinishSignUpUiState(
//    val id: Int = 0,
//    val userId: Int = 0,
//    val name: String = "",
//    val categoryId: Int = 0,
//    val certificate: String = "",
//    val location: String = "",
//    val imageUrl: String = "",
//    val email: String = "",
//    val phoneNumber: String = "",
//    val hourlyRate: String = "",
//    val yearsOfExperience: Int = 0,
//    val rating: Float = 0f,
//    val servicetype: String = "",
//
//    // UI-specific
//    val basicExpanded: Boolean = true,
//    val professionalExpanded: Boolean = false,
//    val servicesExpanded: Boolean = false,
//    val certificationsExpanded: Boolean = false,
//
//    // Optional: UI loading/error state
//    val isLoading: Boolean = false,
//    val errorMessage: String? = null
//)
//
//class FinishSignUpViewModel(
//    private val providerRepository: ProviderRepository
//) : ViewModel() {
//
//    private val _uiState = MutableStateFlow(FinishSignUpUiState())
//    val uiState: StateFlow<FinishSignUpUiState> = _uiState
//
//    fun onValueChanged(field: String, value: String) {
//        _uiState.value = when (field) {
//            "name" -> _uiState.value.copy(name = value)
//            "phoneNumber" -> _uiState.value.copy(phoneNumber = value)
//            "certificate" -> _uiState.value.copy(certificate = value)
//            "location" -> _uiState.value.copy(location = value)
//            "imageUrl" -> _uiState.value.copy(imageUrl = value)
//            "hourlyRate" -> _uiState.value.copy(hourlyRate = value)
//            "serviceType" -> _uiState.value.copy(servicetype = value)
//            "categoryId" -> _uiState.value.copy(categoryId = value.toIntOrNull() ?: 0)
//            "userId" -> _uiState.value.copy(userId = value.toIntOrNull() ?: 0)
//            "yearsOfExperience" -> _uiState.value.copy(yearsOfExperience = value.toIntOrNull() ?: 0)
//            "rating" -> _uiState.value.copy(rating = value.toFloatOrNull() ?: 0f)
//            else -> _uiState.value
//        }
//    }
//
//    fun toggleSection(section: String) {
//        _uiState.value = when (section) {
//            "basic" -> _uiState.value.copy(basicExpanded = !_uiState.value.basicExpanded)
//            "professional" -> _uiState.value.copy(professionalExpanded = !_uiState.value.professionalExpanded)
//            "services" -> _uiState.value.copy(servicesExpanded = !_uiState.value.servicesExpanded)
//            "certifications" -> _uiState.value.copy(certificationsExpanded = !_uiState.value.certificationsExpanded)
//            else -> _uiState.value
//        }
//    }
//
//    fun createProvider(onResult: (Boolean) -> Unit) {
//        val state = _uiState.value
//        val provider = Provider(
//            id = state.id,
//            userId = state.userId,
//            name = state.name,
//            categoryId = state.categoryId,
//            certificate = state.certificate,
//            location = state.location,
//            imageUrl = state.imageUrl,
//            phoneNumber = state.phoneNumber,
//            hourlyRate = state.hourlyRate,
//            yearsOfExperience = state.yearsOfExperience,
//            rating = state.rating,
//            serviceType = state.servicetype
//        )
//
//        _uiState.value = _uiState.value.copy(isLoading = true)
//
//        providerRepository.createProvider(provider).enqueue(object : Callback<Provider> {
//            override fun onResponse(call: Call<Provider>, response: Response<Provider>) {
//                _uiState.value = _uiState.value.copy(isLoading = false)
//                onResult(response.isSuccessful)
//            }
//
//            override fun onFailure(call: Call<Provider>, t: Throwable) {
//                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = t.message)
//                onResult(false)
//            }
//        })
//    }
//
//    fun logout() {
//        _uiState.value = FinishSignUpUiState()
//    }
//}

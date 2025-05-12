package com.example.myapplication.data.model

data class Category(
    val id: Int,
    val name: String
)
enum class ServiceType(val displayName: String) {
    PLUMBER("Plumbing"),
    PAINTER("Electrical"),
    CARPENTER("Carpenter"),
    ELECTRICIAN("Electrician"),
    CONTRACTOR("Car Repair"),
    GARDNER("Gardener")
}
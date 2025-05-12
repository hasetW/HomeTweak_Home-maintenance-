package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Provider
import com.example.myapplication.data.remote.ProviderService
import retrofit2.Call

class ProviderRepository(private val providerService: ProviderService) {

    fun createProvider(provider: Provider): Call<Provider> {
        return providerService.createProvider(provider)
    }

    fun getAllProviders(): Call<List<Provider>> {
        return providerService.getAllProviders()
    }

    fun getProvidersByCategory(categoryId: Int): Call<List<Provider>> {
        return providerService.getProvidersByCategory(categoryId)
    }

    fun searchProviders(name: String): Call<List<Provider>> {
        return providerService.searchProviders(name)
    }

    fun updateProvider(id: Int, provider: Provider): Call<Provider> {
        return providerService.updateProvider(id, provider)
    }

    fun deleteProvider(id: Int): Call<Void> {
        return providerService.deleteProvider(id)
    }
}

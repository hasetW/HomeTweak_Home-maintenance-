package com.example.myapplication.data.remote

import android.content.Context
import com.example.myapplication.data.model.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5000/"

    private lateinit var retrofit: Retrofit
    private lateinit var sessionManager: SessionManager

    fun init(context: Context) {
        sessionManager = SessionManager(context)

        val authInterceptor = AuthInterceptor {
            sessionManager.fetchAuthToken()
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val bookingService: BookingService by lazy {
        retrofit.create(BookingService::class.java)
    }

    val categoryService: CategoryService by lazy {
        retrofit.create(CategoryService::class.java)
    }

    val providerService: ProviderService by lazy {
        retrofit.create(ProviderService::class.java)
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}

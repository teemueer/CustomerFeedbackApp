package com.example.customerfeedbackapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

object BarcodeApi {
    const val BASE_URL = "https://barcodes1.p.rapidapi.com/"

    object Model {
        data class Product(
            val title: String?,
            val manufacturer: String?,
            val description: String?,
            val images: List<String>?)
        data class Result(val product: Product?)
    }

    interface Service {
        @Headers(
            "X-RapidAPI-Key: $API_KEY",
            "X-RapidAPI-Host: barcodes1.p.rapidapi.com"
        )
        @GET(".")
        suspend fun product(@Query("query") query: String): Model.Result
    }

	// logging response into console
    val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: Service by lazy {
        retrofit.create(Service::class.java)
    }
}
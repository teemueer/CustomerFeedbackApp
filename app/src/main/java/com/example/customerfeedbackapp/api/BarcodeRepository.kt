package com.example.customerfeedbackapp.api

class BarcodeRepository {
    private val call = BarcodeApi.service

    suspend fun getProduct(ean: String) = call.product(ean)
}
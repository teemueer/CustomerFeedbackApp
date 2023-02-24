package com.example.customerfeedbackapp.models

data class Product(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val feedback: MutableList<Feedback> = mutableListOf<Feedback>()
) {}

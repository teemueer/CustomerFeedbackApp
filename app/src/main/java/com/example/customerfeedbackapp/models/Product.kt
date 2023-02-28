package com.example.customerfeedbackapp.models

data class Product(
    val EAN: String = "",
    val description: String ="",
    val id:Int = 0,
    val kiloOrKpl:Boolean = true,
    val name: String = "",
    val price: String ="",
    val product_info: String ="",
    val type_id: Int = 0,
    val type_name: String = "",
    val feedbackArray: MutableList<Feedback> = mutableListOf<Feedback>()

){}
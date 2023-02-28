package com.example.customerfeedbackapp.models

data class Product(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val feedback: MutableList<Feedback> = mutableListOf<Feedback>()
) {}

data class ProductBeta(
    val EAN: String = "",
    val description: String ="",
    val id:Int = 0,
    val kiloOrKpl:Boolean = true,
    val name: String = "",
    val product_info: String ="",
    val type_id: Int = 0,
    val type_name: String = "",
    val feedbackArray: MutableList<BetaFeedback> = mutableListOf<BetaFeedback>()

){}

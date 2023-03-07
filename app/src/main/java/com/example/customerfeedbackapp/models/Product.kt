package com.example.customerfeedbackapp.models

data class Product(
    val EAN: String = "",
    val description: String ="",
    val id:Int = 0,
    val kiloOrKpl:Boolean = true,
    val name: String = "",
    val price: String ="",
    val product_info: String ="",
    val imageId: Int  = 0,
    val type_id: Int = 0,
    val type_name: String = "",
    val feedbackArray: MutableList<Feedback> = mutableListOf()

)

data class Product2(
    val ean: String? = null,
    val title: String? = null,
    val manufacturer: String? = null,
    val description: String? = null,
    val images: List<String>? = null,
    val feedback: MutableList<Feedback> = mutableListOf()
)
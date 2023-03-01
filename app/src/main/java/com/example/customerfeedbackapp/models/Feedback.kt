package com.example.customerfeedbackapp.models

data class Feedback(
    val feedback: String = "",
    val rating: Int = 0)

data class BetaFeedback(val rating:String =""){}
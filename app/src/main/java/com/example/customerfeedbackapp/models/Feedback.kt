package com.example.customerfeedbackapp.models

import java.time.LocalDate
import java.util.Date

//data class Feedback2(val rating:String =""){}

data class Feedback(
    val date:String = "",
    val feedback: String = "",
    val rating: Int = 0)
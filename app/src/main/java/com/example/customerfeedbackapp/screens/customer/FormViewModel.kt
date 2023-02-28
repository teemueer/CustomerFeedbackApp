package com.example.customerfeedbackapp.screens.customer

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class FormViewModel(private val stateHandle: SavedStateHandle):ViewModel() {

    private var firestore = FirebaseFirestore.getInstance()
    var state = mutableStateOf<Product?>(null)
    private var id: Int = 0

    init{
        Log.d("DBG", "ProductViewModel init")
        id = stateHandle.get<Int>("product_id") ?: 0
        val handle = firestore.collection("products_beta")
        val ref = handle.whereEqualTo("id", id).get()

        ref.addOnSuccessListener { state.value = it.documents[0].toObject(Product::class.java) }
    }

    fun rate(rating: String, id: Int) {
        val handle = firestore.collection("products_beta")
        val ref = handle.whereEqualTo("id", id ).get()
        ref.addOnSuccessListener {
            val feedback = state.value?.feedbackArray
            feedback?.add(Feedback(rating))
            Log.d("DBG", "$feedback")
            handle.document(it.documents[0].id).update("feedback", feedback)
        }

    }


}
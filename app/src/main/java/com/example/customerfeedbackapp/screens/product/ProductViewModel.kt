package com.example.customerfeedbackapp.screens.product

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.*
import com.google.firebase.firestore.FirebaseFirestore

class ProductViewModel(stateHandle: SavedStateHandle): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    private var ean: String?

    var product by mutableStateOf<Product?>(null)

    init {
        Log.d("DBG", "ProductViewModel init")

        ean = stateHandle.get<String>("ean")
        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("ean", ean).get()

        ref.addOnSuccessListener {
            product = it.documents[0].toObject(Product::class.java)
        }
    }

    fun update(title: String, description: String) {
        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("ean", ean).get()
        ref.addOnSuccessListener {
            handle.document(it.documents[0].id).update(
                "title", title,
                "description", description
            )
        }
    }

    fun sendFeedback(feedback: String, rating: Int) {
        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("ean", ean).get()
        ref.addOnSuccessListener {
            val feedbacks = product?.feedback ?: mutableListOf<Feedback>()
            feedbacks.add(Feedback(feedback, rating))
            Log.d("DBG", "$feedbacks")
            handle.document(it.documents[0].id).update("feedback", feedbacks)
        }
    }

    /*
    fun rate(rating: String) {
        val handle = firestore.collection("products_beta")
        val ref = handle.whereEqualTo("id", id).get()
        ref.addOnSuccessListener {
            val feedback = state.value?.feedbackArray
            feedback?.add(BetaFeedback(rating))
            Log.d("DBG", "$feedback")
            handle.document(it.documents[0].id).update("feedback", feedback)
        }
    }
    */

}
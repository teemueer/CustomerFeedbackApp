package com.example.customerfeedbackapp.screens.product

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class ProductViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    private var id: Int = 0
    var state = mutableStateOf<Product?>(null)

    init {
        Log.d("DBG", "ProductViewModel init")

        id = stateHandle.get<Int>("product_id") ?: 0
        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("id", id).get()

        ref.addOnSuccessListener { state.value = it.documents[0].toObject(Product::class.java) }
    }

    fun update(name: String, description: String) {
        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("id", id).get()
        ref.addOnSuccessListener {
            handle.document(it.documents[0].id).update(
                "name", name,
                "description", description
            )
        }
    }

    fun rate(rating: Int) {
        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("id", id).get()
        ref.addOnSuccessListener {
            val feedback = state.value?.feedback
            feedback?.add(Feedback(rating))
            Log.d("DBG", "$feedback")
            handle.document(it.documents[0].id).update("feedback", feedback)
        }
    }
}
package com.example.customerfeedbackapp.screens.product

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class ProductViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    var state = mutableStateOf<Product?>(null)

    init {
        val id = stateHandle.get<Int>("product_id") ?: 0
        Log.d("DBG", "id: $id")

        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("id", id).get()

        ref.addOnSuccessListener { state.value = it.documents[0].toObject(Product::class.java) }
    }

}
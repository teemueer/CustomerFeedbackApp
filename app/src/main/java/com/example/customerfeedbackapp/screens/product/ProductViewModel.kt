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
            val updatedProduct = Product(id, name, description)
            Log.d("DBG", "$updatedProduct")
            handle.document(it.documents[0].id).set(updatedProduct)
            state.value = updatedProduct
        }
    }
}
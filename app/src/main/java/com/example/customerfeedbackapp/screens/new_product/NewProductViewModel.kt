package com.example.customerfeedbackapp.screens.new_product

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.models.User
import com.google.firebase.firestore.FirebaseFirestore

class NewProductViewModel(): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    var state = mutableStateOf<Product?>(null)

    init {
        Log.d("DBG", "NewProductViewModel init")
    }

    fun save(product: Product) {
        val handle = firestore.collection("products")
        val ref = handle.whereEqualTo("id", product.id).get()

        ref.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result.let {
                    if (it.isEmpty) {
                        handle.add(product)
                    } else {
                        handle.document(it.documents[0].id).set(product)
                    }
                    state.value = product
                }
            }
        }
    }
}
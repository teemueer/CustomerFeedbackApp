package com.example.customerfeedbackapp.screens.new_product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.models.User
import com.google.firebase.firestore.FirebaseFirestore

class NewProductViewModel(): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    var newProduct = MutableLiveData<Product?>(null)

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
                    newProduct.postValue(product)
                }
            }
        }

        /*
        existingProductHandle.addOnSuccessListener {
            handle.document(it.documents[0].id).set(product)
            newProduct.postValue(product)
        }
        */

        /*
        existingProductHandle.addOnFailureListener {
            val newProductHandle = handle.add(product)
            newProductHandle.addOnSuccessListener {
                newProduct.postValue(product)
            }
            newProductHandle.addOnFailureListener {
                Log.d("DBG", "${it.message}")
            }
        }
        */
    }
}
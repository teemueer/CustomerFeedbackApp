package com.example.customerfeedbackapp.screens.customer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.models.Product2
import com.google.firebase.firestore.FirebaseFirestore


class ProductViewModel(): ViewModel(){
    //var currentItem:Product  = Product()
    var currentItem2: Product2 = Product2()
    private var firestore = FirebaseFirestore.getInstance()
    var fb = mutableStateOf<List<Product2>>(emptyList())
    var state = ArrayList<Product2>()
    var product by mutableStateOf<Product2?>(null)

    fun getProductsBCAPI(){
        val handle = firestore.collection("products").get()
        handle.addOnSuccessListener {
            val _products = ArrayList<Product2>()
            for (product in it.documents) {
                product.toObject(Product2::class.java)?.let {
                    _products.add(it)
                    state.add(it)
                }
            }
            fb.value = _products
        }
        handle.addOnFailureListener { Log.d("DBG", "${it.message}") }
    }

    fun rate(feedback:String, rating: Int) {
        val handle = firestore.collection("products")
        val rRef = handle.whereEqualTo("ean", currentItem2.ean).get()
        rRef.addOnSuccessListener {
            product = it.documents[0].toObject(Product2::class.java)
        }

        rRef.addOnSuccessListener {
            val feedbacks = product?.feedback ?: mutableListOf<Feedback>()
            feedbacks.add(Feedback(feedback, rating))
            Log.d("DBG", "$feedbacks")
            handle.document(it.documents[0].id).update("feedback", feedbacks)
        }
    }

}




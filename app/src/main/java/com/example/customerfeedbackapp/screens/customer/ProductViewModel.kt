package com.example.customerfeedbackapp.screens.customer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customerfeedbackapp.api.BarcodeRepository
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.models.Product2
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ProductViewModel() : ViewModel() {
    //var currentItem:Product  = Product()
    var currentItem2: Product2 = Product2()
    private var firestore = FirebaseFirestore.getInstance()
    var fb = mutableStateOf<List<Product2>>(emptyList())
    var state = ArrayList<Product2>()
    var toast by mutableStateOf<String?>(null)
    var product by mutableStateOf<Product2?>(null)
    private val repository = BarcodeRepository()
    var readyToNavigate by mutableStateOf<Boolean>(false)

    fun getProductsBCAPI() {
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

    fun rate(feedback: String, rating: Int) {
        val handle = firestore.collection("products")
        val rRef = handle.whereEqualTo("ean", currentItem2.ean).get()
        rRef.addOnSuccessListener {
            product = it.documents[0].toObject(Product2::class.java)
        }

        val formatter = SimpleDateFormat.getDateTimeInstance()
        val date = Date()
        val current = formatter.format(date)

        rRef.addOnSuccessListener {
            val feedbacks = product?.feedback ?: mutableListOf<Feedback>()
            feedbacks.add(Feedback(current,feedback, rating))
            Log.d("DBG", "$feedbacks")
            handle.document(it.documents[0].id).update("feedback", feedbacks)
        }
    }

    fun addProduct(ean: String) {
        val handle = firestore.collection("products")
        val existingProduct = handle.whereEqualTo("ean", ean).get()

        existingProduct.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result.let {
                    if (it.isEmpty) {
                        viewModelScope.launch(Dispatchers.IO) {
                            val product = repository.getProduct(ean).product
                            if (product != null) {
                                handle.add(
                                    Product2(
                                        ean,
                                        product.title,
                                        product.manufacturer,
                                        product.description,
                                        product.images
                                    )
                                )
                                readyToNavigate = true
                            } else {
                                toast = "Product with EAN $ean was not found."
                            }
                        }
                    } else {
                        toast = "Product already in database."
                    }
                }
            }
        }
    }
}







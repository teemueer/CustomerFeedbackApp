package com.example.customerfeedbackapp.screens.customer

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customerfeedbackapp.api.BarcodeRepository
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.models.Product2
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class ProductViewModel() : ViewModel() {
    //var currentItem:Product  = Product()

    var currentItem2: Product2 = Product2()
    var currentFeedbackSelected: Int = 0

    private var firestore = FirebaseFirestore.getInstance()

    var fb by mutableStateOf<List<Product2>>(emptyList())

    var state = ArrayList<Product2>()
    var toast by mutableStateOf<String?>(null)
    var product by mutableStateOf<Product2?>(null)
    private val repository = BarcodeRepository()
    var readyToNavigate by mutableStateOf<Boolean>(false)

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        firestore.collection("products").snapshots().map {
            it.toObjects(Product2::class.java)
        }.collect {
            fb = it
        }
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
            Log.d("DBG", "$readyToNavigate")
            handle.document(it.documents[0].id).update("feedback", feedbacks)
            readyToNavigate = true
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







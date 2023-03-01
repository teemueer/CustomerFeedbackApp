package com.example.customerfeedbackapp.screens.new_product

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customerfeedbackapp.api.BarcodeRepository
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewProductViewModel(): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    private val repository = BarcodeRepository()

    var readyToNavigate by mutableStateOf<Boolean>(false)
    var toast by mutableStateOf<String?>(null)

    init {
        Log.d("DBG", "NewProductViewModel init")
    }

    fun save(ean: String) {
        val handle = firestore.collection("products")
        val existingProduct = handle.whereEqualTo("ean", ean).get()

        existingProduct.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result.let {
                    if (it.isEmpty) {
                        // the EAN was not found in our Firestore database
                        viewModelScope.launch(Dispatchers.IO) {
                            // fetch data from the Barcode API
                            val product = repository.getProduct(ean).product
                            if (product != null) {
                                handle.add(Product(
                                    ean,
                                    product.title,
                                    product.manufacturer,
                                    product.description,
                                    product.images
                                ))
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
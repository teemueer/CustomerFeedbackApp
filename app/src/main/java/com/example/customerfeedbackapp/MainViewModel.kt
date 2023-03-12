package com.example.customerfeedbackapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel(): ViewModel() {
	// firestore instance
    private var firestore = FirebaseFirestore.getInstance()
	
	// firebase user
    var currentUser = MutableLiveData<User?>(null)

	// new user registration to firebase
    fun register(email: String, password: String, isOwner: Boolean) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d("DBG", "register success")
                val firebaseUser = firebaseAuth.currentUser
                firebaseUser?.let {
                    val user = User(it.uid, email)
                    saveUser(user)
                }
            }
            .addOnFailureListener {
                Log.d("DBG", "${it.message}")
            }
    }

	// user login to firebase
    fun login(email: String, password: String) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                firebaseUser?.let {
                    currentUser.postValue(User(it.uid, it.email))
                    //getUser(it.uid)
                }
            }
            .addOnFailureListener {
                Log.d("DBG", "${it.message}")
            }
    }

	// user logout from firebase
    fun logout() {
        FirebaseAuth.getInstance().signOut()
        currentUser.postValue(null)
    }

	// save new user to firestore
    fun saveUser(user: User) {
        val handle = firestore.collection("users").document(user.uid).set(user)
        handle.addOnSuccessListener {
            currentUser.postValue(user)
            Log.d("DBG", "Current user $currentUser.value")
        }
        handle.addOnFailureListener { Log.d("DBG", "${it.message}") }
    }

	// get user from firestore
    fun getUser(uid: String) {
        val handle = firestore.collection("users").document(uid).get()
        handle.addOnSuccessListener {
            currentUser.postValue(it.toObject(User::class.java))
            Log.d("DBG", "Current user ${it.toObject(User::class.java)}")
        }
        handle.addOnFailureListener { Log.d("DBG", "${it.message}") }
    }
}

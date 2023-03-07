package com.example.customerfeedbackapp.permissions

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PermissionViewModel: ViewModel(){

    //Works with the idea of FIFO
    val visiblePermissionDialogue = mutableStateListOf<String>()

    fun dismissDialog(){
        visiblePermissionDialogue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ){
        if(!isGranted && !visiblePermissionDialogue.contains(permission)){
            visiblePermissionDialogue.add(permission)
        }
    }

}
package com.example.madina

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madina.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class BaseViewModel<N> :ViewModel(){
    val messageLivedata = MutableLiveData<String>()
    val showLoading = MutableLiveData<Boolean>()
    var navigator:N?=null

    fun getUserId():String{

        var user = FirebaseAuth.getInstance().currentUser?.uid

        return user.toString()

    }

}



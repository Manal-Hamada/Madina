package com.example.madina.profile

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madina.BaseViewModel
import com.example.madina.database.signIn
import com.example.madina.login.LoginActivity
import com.example.madina.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileViewModel:BaseViewModel<Navigator>() {

    var name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val phoneNum = MutableLiveData<String>()
    val colege = MutableLiveData<String>()
    val buildingNum =MutableLiveData<String>()
    val roomNum = MutableLiveData<String>()



    fun logOut(){
      navigator?.getContext().let {
                val intent = Intent(it, LoginActivity::class.java)
          navigator?.openLoginScreen()
                Log.e("error", "hi")
            }
    }



}
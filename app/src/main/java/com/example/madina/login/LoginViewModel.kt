package com.example.madina.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.madina.utils.BaseViewModel
import com.example.madina.database.signIn
import com.example.madina.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel: BaseViewModel<LoginNavigator>(){


    var email =MutableLiveData<String>()
    var emailError =MutableLiveData<String>()
    var pass = MutableLiveData<String>()
    var passError = MutableLiveData<String>()

    var fAauth :FirebaseAuth=FirebaseAuth.getInstance()
    var auth =Firebase.auth


    fun login(){

      showLoading.value=true;
      auth.signInWithEmailAndPassword((email.value.toString().trim()), pass.value.toString().trim())
          .addOnCompleteListener { task ->
              showLoading.value=false
              if (task.isSuccessful){
                  Log.e("firebase","succeful login")
                 // messageLivedata.value=task.exception?.localizedMessage
                 //navigator?.openHomeScreen()
                  checkUserFromFireStore(task.result.user!!
                      .uid)
              }
              else{
                  Log.e("firebase","faild login "+task.exception?.localizedMessage)
                  messageLivedata.value=task.exception?.localizedMessage}
          }
  }

fun checkCurrentUser(){

    val user :FirebaseUser?=fAauth.currentUser
    if(user!=null)
        navigator?.openHomeScreen()

  }
fun checkUserFromFireStore(uid:String){
    showLoading.value=false
    signIn(uid!!, onSuccessListener =
    {documentSnapshot->
       val user= documentSnapshot.toObject(AppUser::class.java)
        if(user==null){
            messageLivedata.value="Invailed email or password"
        return@signIn}
        else
            navigator?.openHomeScreen()
    }
        , onfaliurListener = {
            showLoading.value=false
            messageLivedata.value=it.localizedMessage
        })
}

    fun resetPass(){

           fAauth .sendPasswordResetEmail("user@example.com")
            .addOnCompleteListener{
                if(it.isSuccessful()){
                    Log.d(TAG,"Email sent.")
                }
            }
    }
    fun forgetPassword(){
    navigator?.openForgetPasswordScreen()
}

    }





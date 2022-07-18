package com.example.madina.login

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madina.BaseViewModel
import com.example.madina.LoginNavigator
import com.example.madina.database.signIn
import com.example.madina.model.AppUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:BaseViewModel<Navigator>(){


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
              if (task.isSuccessful){
                  Log.e("firebase","succeful login")
                 // messageLivedata.value=task.exception?.localizedMessage
                //navigator?.openHomeScreen()
                 checkUserFromFireStore(task.result.user!!.uid,task)
              }
              else{
                  Log.e("firebase","faild login "+task.exception?.localizedMessage)
                  messageLivedata.value=task.exception?.localizedMessage+" please reset your data"
                  showLoading.value=false
              }
          }
  }


fun checkUserFromFireStore(uid:String,task:Task<AuthResult>){
    signIn(uid!!,AppUser.COLLECTION_NAME, onSuccessListener =
    {documentSnapshot->
        showLoading.value=false
       val user= documentSnapshot.toObject(AppUser::class.java)
        if(user==null){
            messageLivedata.value="there is no data attatched by this email"
        return@signIn}
        else
            navigator?.openHomeScreen()
    }
        , onfaliurListener = {
            showLoading.value=false
            messageLivedata.value="check your connection"
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





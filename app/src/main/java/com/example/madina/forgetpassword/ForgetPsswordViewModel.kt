package com.example.madina.forgetpassword

import android.service.controls.ControlsProviderService.TAG

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.madina.utils.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgetPsswordViewModel : BaseViewModel<ForgetPasswordNavigator>(){

    var emaiId=MutableLiveData<String>()
    lateinit var toast:Toast

    var auth = Firebase.auth
    var fAauth : FirebaseAuth = FirebaseAuth.getInstance()

    fun checkEdtText(){
        if(emaiId.value.toString().trim().equals(null)){
        toast= Toast.makeText(navigator?.setContext()," please enter your email id",Toast.LENGTH_SHORT)
        toast.show()}

        else{
            fAauth .sendPasswordResetEmail(emaiId.value.toString().trim())
                .addOnCompleteListener{
                    if(it.isSuccessful()){
                        Log.d(TAG,"Email sent.")
                        toast= Toast.makeText(navigator?.setContext(),"email sent succefuly",Toast.LENGTH_SHORT)
                        toast.show()

                    }
                    else
                        toast= Toast.makeText(navigator?.setContext(),it.exception?.message.toString(),Toast.LENGTH_SHORT)
                    toast.show()
                }

        }
    }
}
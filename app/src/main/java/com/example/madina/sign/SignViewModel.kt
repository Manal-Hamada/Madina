package com.example.madina.sign

import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madina.BaseViewModel
import com.example.madina.LoginNavigator
import com.example.madina.R
import com.example.madina.database.addUserToFirestore
import com.example.madina.database.signIn
import com.example.madina.model.AppUser
import com.example.madina.profile.ProfileFragment
import com.example.madina.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignViewModel:BaseViewModel<Navigator>( ) {

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val ssn = MutableLiveData<String>()
    val phoneNum = MutableLiveData<String>()
    val adress = MutableLiveData<String>()
    val colege = MutableLiveData<String>()
    val buildingNum = MutableLiveData<String>()
    val roomNum = MutableLiveData<String>()
    val spinner = MutableLiveData<String>()

    var auth = Firebase.auth
    var user:AppUser?=null


   // lateinit var viewModel:ProfileViewModel

    fun login() {
        showLoading.value = true;
        auth.createUserWithEmailAndPassword(
            (email.value.toString().trim()),
            password.value.toString().trim()
        )
            .addOnCompleteListener { task ->
               // showLoading.value = false
                if (task.isSuccessful) {
                    Log.e("firebase", "succeful registeration")
                   // messageLivedata.value = task.exception?.localizedMessage
                    createFirestoreUser(task.result.user!!.uid)

                } else {
                    Log.e("firebase", "faild registeration " + task.exception?.localizedMessage)
                    messageLivedata.value = task.exception?.localizedMessage
                }
            }
    }

    fun createFirestoreUser(uid: String) {
      //  showLoading.value = true
        val user = AppUser(
            id = uid,
            name = name.value,
            email = email.value,
            password = password.value,
            phoneNum = phoneNum.value,
            adress = adress.value,
            roomNum = roomNum.value,
            buildingNum = buildingNum.value,
            colleage = colege.value,
            ssn = ssn.value
        )
        addUserToFirestore(user,
            onSuccessListener = {
                showLoading.value=false
               val profile=ProfileFragment()
              //  profile.fUser=user.id.toString()
               // Log.e("fuser:",profile.fUser.toString())
                //checkUserFromFireStore(uid)
                navigator?.openHomeScreen()
                                } ,
            onFaliurListener ={
                messageLivedata.value=it.localizedMessage

        } )
    }
    fun checkUserFromFireStore(uid:String){
        showLoading.value=false
        signIn(uid,AppUser.COLLECTION_NAME ,onSuccessListener =

        {documentSnapshot->
            val user= documentSnapshot.toObject(AppUser::class.java)
            if(user==null){
                messageLivedata.value="Invailed email or password"
                return@signIn}
            else
              navigator?.openHomeScreen()
          //  messageLivedata.value="sucess"
            Log.e("error","success")
        }
            , onfaliurListener = {
                showLoading.value=false
                messageLivedata.value=it.localizedMessage
            })
    }


    }
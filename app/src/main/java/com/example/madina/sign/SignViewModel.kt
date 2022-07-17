package com.example.madina.sign

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.madina.utils.BaseViewModel
import com.example.madina.database.addUserToFirestore
import com.example.madina.model.AppUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignViewModel: BaseViewModel<SignupNavigator>( ) {

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val ssn = MutableLiveData<String>()
    val phoneNum = MutableLiveData<String>()
    val adress = MutableLiveData<String>()
    val colege = MutableLiveData<String>()
    val buildingNum = MutableLiveData<String>()
    val roomNum = MutableLiveData<String>()

    var auth = Firebase.auth


    fun login() {

        showLoading.value = true;

        auth.createUserWithEmailAndPassword(
            (email.value.toString().trim()),
            password.value.toString().trim()
        )
            .addOnCompleteListener { task ->
                showLoading.value = false
                if (task.isSuccessful) {
                    Log.e("firebase", "succeful registeration")
                    messageLivedata.value = task.exception?.localizedMessage
                    createFirestoreUser(task.result.user!!.uid)
                    navigator?.openHomeScreen()
                } else {
                    Log.e("firebase", "faild registeration " + task.exception?.localizedMessage)
                    messageLivedata.value = task.exception?.localizedMessage
                }
            }
    }

    fun createFirestoreUser(uid: String) {
        showLoading.value = true
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
                navigator?.openHomeScreen()
                                } ,
            onFaliurListener ={
                showLoading.value=false
                messageLivedata.value=it.localizedMessage

        } )
    }

}
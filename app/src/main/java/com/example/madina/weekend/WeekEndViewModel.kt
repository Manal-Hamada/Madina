package com.example.madina.weekend

import android.provider.SyncStateContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madina.BaseViewModel
import com.example.madina.database.getCollection
import com.example.madina.database.signIn
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WeekEndViewModel:BaseViewModel<Navigator>() {
    val db = Firebase.firestore
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

  fun getAllVacations() {
        val userId = getUserId()
        showLoading.value = true
        getCollection("vacations")
            .whereEqualTo("userId", userId)
            .get().addOnSuccessListener { documents ->
                 navigator?.listAllVacationsFromFireBase(documents.toObjects(WeekEndModel::class.java))
                showLoading.value = false
            }.addOnFailureListener {
           messageLivedata.value="check your connect please"
        }

    }
}


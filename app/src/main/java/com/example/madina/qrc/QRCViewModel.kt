package com.example.madina.qrc

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class QRCViewModel (): ViewModel() {


    val sdn: MutableLiveData<String> = MutableLiveData<String>("");
    val db = Firebase.firestore

    var stopProgressBar: MutableLiveData<String> = MutableLiveData<String>("");
    var failMessage: MutableLiveData<String> = MutableLiveData<String>("");
    var userFoundedGotBefore: MutableLiveData<String> = MutableLiveData<String>("");

    fun checkIfGetBeforeOrNot(){
        // getAllSdnDocuments()





    }

    fun getAllSdnDocuments(usersdn:String) {

        var isfounded = false
        Log.e("get all data fun", "view model")
        db.collection("QrcCollection")
            .get()
            .addOnSuccessListener {
                    result ->
                for (document in result) {
                    val data : MutableMap<String, Any> =document.data
                    if(data.containsValue(usersdn)){
                        Log.e("sdn founded ", "${document.id} => ${document.data}")
                        stopProgressBar.value="stop"
                        userFoundedGotBefore.value="done"
                        isfounded = true
                        break
                    }
                }
                if(isfounded==false){

                    addDocumentToDataBase(usersdn)

                }
            }
            .addOnFailureListener { exception ->
                Log.e("sdn from data base", "Error getting documents ", exception)
                stopProgressBar.value="stop"
                failMessage.value="يوجد مشكلة بالانترنت"
            }

    }

    private fun addDocumentToDataBase(usersdn: String) {
        Log.e("add doc", "")

        val data=hashMapOf(
            "sdn" to "${usersdn}"

        )
        db.collection("QrcCollection").add(data)
            .addOnSuccessListener{

                    documentReference -> Log.e("add success","DocumentSnapshot written with ID:${documentReference.id}")
                stopProgressBar.value="stop"
                userFoundedGotBefore.value="not"

            }
            .addOnFailureListener{
                    e->Log.e("add fail ","Error adding document",e)
                stopProgressBar.value="stop"
                failMessage.value="يوجد مشكلة بالانترنت"

            }



    }


}


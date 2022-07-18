package com.example.madina.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


object  Constants {
    val taxesRef: String="needsTaxes"
    var sdn: String =""
    var userAotoId: String =""
     var createButtonIsEnabled =true;
     val db = Firebase.firestore
    val vacationRef = "vacations"

    fun checkConnectionType(context: Context):Boolean
    {

        var isConnected =  true
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi_Connection = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile_data_connection = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifi_Connection!!.isConnectedOrConnecting)
        {
           isConnected=true
        }
        else
        {
            if (mobile_data_connection!!.isConnectedOrConnecting)
            {
                isConnected=true
            }
            else
            {
                isConnected=false
            }
        }
        return isConnected
    }



    public fun getUserId():String{
        val user_id = FirebaseAuth.getInstance().currentUser?.uid
        Log.e("userid",user_id+"")
        userAotoId=user_id!!
        return user_id!!
    }
 }
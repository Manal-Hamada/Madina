package com.example.madina.utils

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object  Constants {
    val taxesRef: String="needsTaxes"
    var sdn: String =""
     var createButtonIsEnabled =true;
     val db = Firebase.firestore
    val vacationRef = "vacations"

 }
package com.example.madina.database

import com.example.madina.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun addUserToFirestore(user:AppUser,onSuccessListener:OnSuccessListener<Void>,onFaliurListener:OnFailureListener){
    val db =Firebase.firestore
    val userCollection=getCollection(AppUser.COLLECTION_NAME)
    val userDocument=userCollection.document(user.id!!)
    userDocument.set(user).addOnSuccessListener (onSuccessListener).
            addOnFailureListener(onFaliurListener)

}
fun signIn(uid:String,onSuccessListener:OnSuccessListener<DocumentSnapshot>
           ,onfaliurListener:OnFailureListener){

    val collectionRef=getCollection(AppUser.COLLECTION_NAME)
    collectionRef.document(uid)
        .get()
        .addOnSuccessListener (onSuccessListener)
        .addOnFailureListener(onfaliurListener)
}
fun getCollection(collectionName:String):CollectionReference{
    val db =Firebase.firestore
    val collectionRef=db.collection(collectionName)
    return collectionRef
}
package com.example.madina.model

data class AppUser(var id:String?=null,
                   var email:String?=null,
                   var password :String?=null,
                   var ssn:String?=null,
                   var phoneNum:String?=null,
                   var adress:String?=null,
                   var colleage:String?=null,
                   var buildingNum:String?=null,
                   var roomNum:String?=null,
                   var name:String?=null
                   ) {

    companion object {
        const val COLLECTION_NAME = "users_data"
    }
}
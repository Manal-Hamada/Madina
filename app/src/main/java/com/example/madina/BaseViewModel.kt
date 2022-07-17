package com.example.madina

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madina.model.AppUser

abstract class BaseViewModel<N> :ViewModel(){
    val messageLivedata = MutableLiveData<String>()
    val showLoading = MutableLiveData<Boolean>()
    var navigator:N?=null
    var userId:String?=null


}
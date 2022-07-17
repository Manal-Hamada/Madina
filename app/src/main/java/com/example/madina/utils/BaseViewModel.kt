package com.example.madina.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N> :ViewModel(){
    val messageLivedata = MutableLiveData<String>()
    val showLoading = MutableLiveData<Boolean>()
    var navigator:N?=null



}
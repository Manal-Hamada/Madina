package com.example.madina.sign

import android.content.Context
import com.example.madina.login.LoginActivity

interface Navigator {
    fun openHomeScreen()
    fun openLoginActivity()
    fun getContext():Context


}
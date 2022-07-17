package com.example.madina.forgetpassword

import android.content.Context

interface ForgetPasswordNavigator {
    fun openLoginActivity()
    fun setContext(): Context
}
package com.example.madina.forgetpassword

import android.content.Context

interface Navigator {
    fun openLoginActivity()
    fun setContext(): Context
}
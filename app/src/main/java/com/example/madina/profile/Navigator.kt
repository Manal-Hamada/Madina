package com.example.madina.profile

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface Navigator {

    fun openLoginScreen()
    fun getContext():Context
}
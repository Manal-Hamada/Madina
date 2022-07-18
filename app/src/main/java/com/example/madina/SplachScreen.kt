package com.example.madina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.madina.databinding.ActivitySplachScreenBinding
import com.example.madina.forgetpassword.ForgetPasswordActivity
import com.example.madina.login.LoginActivity
import com.example.madina.login.LoginViewModel
import com.example.madina.sign.SignActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplachScreen : BaseActivity<ActivitySplachScreenBinding,LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


         Handler().postDelayed({
             checkCurrentUser()
         },300)
        viewModel.showLoading.value=false

          }

    fun checkCurrentUser(){

       viewModel.showLoading.value=true
        val user : FirebaseUser?=viewModel.fAauth.currentUser
        if(user!=null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun getLayout(): Int {
      return R.layout.activity_splach_screen
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    fun hideStatusbar(){

    }

    override fun onPause() {
        super.onPause()
    hideLoading()
    }
}
package com.example.madina.forgetpassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.madina.BaseActivity
import com.example.madina.databinding.ActivityForgetPasswordBinding
import com.example.madina.login.LoginActivity
import com.example.madina.R
import com.example.madina.login.LoginViewModel

class ForgetPasswordActivity:BaseActivity<ActivityForgetPasswordBinding,ForgetPsswordViewModel>(),Navigator {

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          viewDataBinding.fvm=viewModel
         viewModel.navigator=this

      }


    override fun openLoginActivity() {
        val intent =Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun setContext(): Context{

        return this
    }

    override fun getLayout(): Int {
        return R.layout.activity_forget_password
    }

    override fun initViewModel(): ForgetPsswordViewModel {
        return ViewModelProvider(this).get(ForgetPsswordViewModel::class.java)
    }

}
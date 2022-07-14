package com.example.madina.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.madina.BaseActivity
import com.example.madina.HomeActivity
import com.example.madina.LoginNavigator
import com.example.madina.R
import com.example.madina.databinding.ActivityLoginBinding
import com.example.madina.forgetpassword.ForgetPasswordActivity
import com.example.madina.sign.SignActivity
import com.example.madina.sign.SignViewModel


class LoginActivity :BaseActivity<ActivityLoginBinding,LoginViewModel>(),Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.lvm= viewModel
        viewModel.navigator=this
        viewModel.checkCurrentUser()
         setLoginBtnClickListener()
         viewModel.resetPass()

        }


    fun validate(): Boolean {

        var validate = true
        if ( viewModel.email.value.equals(null)) {
            viewDataBinding.mailLayout.error="Please enter your email"
             //  viewModel.emailError.value="Please enter your email"
            validate = false
        }
        if ( viewModel.pass.value .equals(null)) {
          viewDataBinding.passLayout.error = "Please enter your password"
            validate = false
        } else
            validate = true

        return validate
    }

    fun setLoginBtnClickListener(){
        viewDataBinding.sigInBtn.setOnClickListener {
            if(validate())
                viewModel.login()

        }
    }



    override fun openHomeScreen() {
       val intent =Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    override fun  openRegesterScreen() {
        val intent =Intent(this,SignActivity::class.java)
        startActivity(intent)
    }

    override fun openForgetPasswordScreen() {
        val intent =Intent(this,ForgetPasswordActivity::class.java)
        startActivity(intent)
    }


    override fun getLayout(): Int {
         return R.layout.activity_login}

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }
}

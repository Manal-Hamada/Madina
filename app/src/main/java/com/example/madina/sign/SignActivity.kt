package com.example.madina.sign

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.madina.HomeActivity
import com.example.madina.databinding.ActivitySignBinding
import com.example.madina.R
import com.example.madina.utils.BaseActivity

class SignActivity : BaseActivity<ActivitySignBinding, SignViewModel>(),SignupNavigator {


    lateinit var model:SignModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  viewModel= SignViewModel()
        viewDataBinding.svm=viewModel
        viewModel.navigator=this
         setRegesterBtnClickListener()
    }


    fun validate(): Boolean {

        var validate = true
        if (viewModel.name.value=="") {
          viewDataBinding.namelayout.error="Please enter your name"
            validate = false
        }
        if (viewModel.adress.value .equals(null)) {
            viewDataBinding.addressLayout.error = "Please enter your adress"
            validate = false
        }
        if (viewModel.colege.value .equals(null)) {
            viewDataBinding.colleage.error = "Please enter your colege name"
            validate = false
        }
        if (viewModel.email.value .equals(null)) {
            viewDataBinding.mailLayout.error = "Please enter your  email"
            validate = false
        }
        if (viewModel.password.value .equals(null)) {
            viewDataBinding.passwordLayout.error = "Please enter your password"
            validate = false
        }

        if (viewModel.phoneNum.value .equals(null)) {
            viewDataBinding.phoneLayout.error = "Please enter your phone number"
            validate = false
        }
        if (viewModel.roomNum.value .equals(null)) {
            viewDataBinding.roomNum.error = "Please enter your room number"
            validate = false
        }
        if (viewModel.ssn.value.equals(null)) {
            viewDataBinding.ssnLayout.error = "Please enter your ssn"
            validate = false
        }
        if (viewModel.buildingNum.value .equals(null)) {
            viewDataBinding.buildingNum.error = "Please enter your building number"
            validate = false
        }
        else
            validate = true

        return validate
    }

    fun setRegesterBtnClickListener(){

        viewDataBinding.regesterBtn.setOnClickListener {
           if( validate())
               viewModel.login()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_sign
    }

    override fun initViewModel(): SignViewModel {
       return ViewModelProvider(this).get(SignViewModel::class.java)
    }

    override fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


}
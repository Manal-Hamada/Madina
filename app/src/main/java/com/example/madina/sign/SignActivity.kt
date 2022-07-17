package com.example.madina.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.madina.BaseActivity
import com.example.madina.HomeActivity
import com.example.madina.LoginNavigator
import com.example.madina.databinding.ActivitySignBinding
import com.example.madina.R
import com.example.madina.login.LoginActivity

class SignActivity : BaseActivity<ActivitySignBinding,SignViewModel>(),Navigator {


    lateinit var model: SignModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  viewModel= SignViewModel()
        viewDataBinding.svm = viewModel
        viewModel.navigator = this
        setSpinner(viewModel.buildingNum,viewDataBinding.coleageSpiner,R.array.colages)
        setSpinner(viewModel.colege,viewDataBinding.spiner,R.array.building_names)
        setRegesterBtnClickListener()


    }


    fun validate(): Boolean {
        var validate = true

        if (viewModel.name.value.equals(null)) {
            viewDataBinding.namelayout.error = "Please enter your full name"
            var toast= Toast.makeText(this," please enter your name ",Toast.LENGTH_SHORT)
            toast.show()
            validate = false
        }

        if (viewModel.adress.value.equals(null)) {
            viewDataBinding.addressLayout.error = "Please enter your adress"
            var toast= Toast.makeText(this," please enter your adress ",Toast.LENGTH_SHORT)
            toast.show()
            validate = false
        }

        if (viewModel.email.value.equals(null)) {
            viewDataBinding.mailLayout.error = "Please enter your  email"
            validate = false
        }
        if (viewModel.password.value.equals(null)) {
            viewDataBinding.passwordLayout.error = "Please enter your password"

            validate = false
        }

        if (viewModel.phoneNum.value.equals(null)) {
            viewDataBinding.phoneLayout.error = "Please enter your phone number"
            var toast= Toast.makeText(this," please enter your phone number ",Toast.LENGTH_SHORT)
            toast.show()
            validate = false
        }
        if (viewModel.phoneNum.value.toString().length != 11) {
            viewDataBinding.phoneLayout.error = "invailed phone number"
            var toast= Toast.makeText(this," invailed phone number ",Toast.LENGTH_SHORT)
            toast.show()
            validate = false
        }

        if (viewModel.roomNum.value.equals(null)) {
            viewDataBinding.roomNum.error = "Please enter your room number"

            validate = false
        }
        if (viewModel.roomNum.value.toString().length > 3) {
            viewDataBinding.roomNum.error = "invailed room number"
            validate = false
        }
        if (viewModel.ssn.value.equals(null)) {
            viewDataBinding.ssnLayout.error = "Please enter your ssn"
            var toast= Toast.makeText(this," Please enter your ssn ",Toast.LENGTH_SHORT)
            toast.show()
            validate = false
        }
        if (viewModel.ssn.value.toString().length < 14) {
            viewDataBinding.ssnLayout.error = "invailed ssn"
            var toast= Toast.makeText(this," invailed ssn ",Toast.LENGTH_SHORT)
            toast.show()
            validate = false
        }
        return validate
    }
        fun setRegesterBtnClickListener() {
            viewDataBinding.regesterBtn.setOnClickListener {
                if(validate())
                    viewModel.login()
            }
        }


    fun setSpinner(setView: MutableLiveData<String>,mySpiner:Spinner,name:Int) {
        val building = resources.getStringArray(name)
        var spiner = mySpiner
        if (spiner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,building)

            spiner.adapter = adapter
            spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                  setView.value=building[position].toString()
                   Toast.makeText(
                        this@SignActivity,
                        getString(androidx.compose.ui.R.string.selected) + " " +
                                " " + building[position], Toast.LENGTH_SHORT
                    ).show()

                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@SignActivity, "selected ", Toast.LENGTH_SHORT)
                        .show()

                }
            }
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun getContext(): Context {
       return this
    }
    override fun onPause() {
        super.onPause()
        hideLoading()
    }
}
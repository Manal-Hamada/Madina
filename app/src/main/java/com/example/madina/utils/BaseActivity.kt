package com.example.madina.utils

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

open abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel<*>>:AppCompatActivity() {


    lateinit var viewModel: VM
    lateinit var viewDataBinding: DB
    var fAauth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        viewModel = initViewModel()
        subscribeToLiveData()
    }

    abstract fun getLayout(): Int
    abstract fun initViewModel(): VM
    fun subscribeToLiveData() {
        viewModel.messageLivedata.observe(
            this, {
                showDialoge(it,"OK")
            })

        viewModel.showLoading.observe(this, {show->
            if(show)
                showLoading()
            else
                hideLoading()

        })

    }

    var alertDialog: AlertDialog? = null
    fun showDialoge(
        message: String?, postActionName: String?= null,
        postAction: DialogInterface.OnCancelListener? = null ,
        negatActionName: String? = null,
        negatAction: DialogInterface.OnCancelListener? = null,
        cancelable: Boolean = true
    ) {
        val defaultAction = object : DialogInterface.OnCancelListener {
            override fun onCancel(dialog: DialogInterface?) {
                dialog?.dismiss()
            }

        }
        val builder = AlertDialog.Builder(this).setMessage(message)
        if (postActionName!=null)
            builder.setPositiveButton(postActionName, postAction?:defaultAction)
            if (negatActionName != null) builder.setNegativeButton(negatActionName, postAction ?: defaultAction)
            builder.setCancelable(cancelable)
            alertDialog = builder.show()


    }

    fun hideAlertDialog() {
        alertDialog?.dismiss()
        alertDialog = null
    }

    var progresDialog: ProgressDialog? = null
    fun showLoading() {
        progresDialog = ProgressDialog(this)
        progresDialog?.setMessage("Loading...")
        progresDialog?.setCancelable(false)
        progresDialog?.show()
    }

    fun hideLoading(){
        progresDialog?.dismiss()
        progresDialog=null
    }
}
fun AlertDialog.Builder.setNegativeButton(negActionName: String?, onCancelListener: DialogInterface.OnCancelListener) {

}

fun AlertDialog.Builder.setPositiveButton(postActionName: String?, onOklListener: DialogInterface.OnCancelListener) {

}

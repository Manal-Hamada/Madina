package com.example.madina.qrc

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.madina.BaseActivity
import com.example.madina.R
import com.example.madina.databinding.ActivityScanBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class ScanActivity : AppCompatActivity() {
    lateinit var binding :ActivityScanBinding

    lateinit var  viewModel : QRCViewModel

    val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        getResult(result)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(QRCViewModel::class.java)



        binding?.scanBtn!!.setOnClickListener(View.OnClickListener {
            onScanBtnClick()
            // viewModel.stopProgressBar.value=""
        })


    }

    private fun observeDataFromViewModel() {
// prog , fail , done before m not  done
        viewModel.stopProgressBar.observe(this,
            Observer {
            if(it.equals("stop")){
                binding.progressbar.isVisible=false
            }

            viewModel.userFoundedGotBefore.observe(this, Observer {
                if(it.equals("done")){
                    showDialog("استحقاق الوجبة","هذا الطالب اخذ وجبته من قبل")
                }else if(it.equals("not")){
                    // dialog
                    showDialog("استحقاق الوجبة","لم يأخذ من قبل")
                }
            })



        })






    }

    fun showDialog(title:String,message: String){


        val alert = AlertDialog.Builder(this).setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener
            { dialogInterface, i -> dialogInterface.dismiss() }).show()

    }

    private fun getResult(result: ScanIntentResult) {
        binding.progressbar.isVisible=true
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            //create and compare with the table
            var userSdn = result.contents
            Log.e("scaned", userSdn)

            checkIfGetBeforeOrNot(userSdn)
            Toast.makeText(
                this,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun checkIfGetBeforeOrNot(sdn:String) {
        //get FromView Model
        Log.e("check fun", "")

        viewModel.getAllSdnDocuments(sdn)
        observeDataFromViewModel()

    }


    // Launch
    private fun onScanBtnClick() {
        viewModel.userFoundedGotBefore.value=""
        viewModel.failMessage.value=""
        barcodeLauncher.launch(ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        })
    }



}
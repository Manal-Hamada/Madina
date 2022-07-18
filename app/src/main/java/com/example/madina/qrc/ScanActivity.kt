package com.example.madina.qrc

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.madina.R
import com.example.madina.databinding.ActivityScanBinding
import com.example.madina.utils.Constants
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class ScanActivity : AppCompatActivity()  , QrcNavigator{

    lateinit var binding : ActivityScanBinding

    lateinit var  viewModel : QRCViewModel

    val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) {
            result: ScanIntentResult ->
        getResult(result)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityScanBinding.inflate(layoutInflater)
         setContentView(binding.root)
       viewModel = ViewModelProvider(this).get(QRCViewModel::class.java)

       viewModel.navigator=this
        if(Constants.checkConnectionType(this) ==false){
           binding!!.noWifi.setImageResource(R.drawable.wifioff)
            binding.cleardata.visibility=Button.GONE
            binding.scanBtn.visibility = Button.GONE
        }else{


        binding?.scanBtn!!.setOnClickListener(View.OnClickListener {
              onScanBtnClick()
             // viewModel.stopProgressBar.value=""
          })

        binding.cleardata.setOnClickListener {
            viewModel.clearAllScannedQrc()
        }

        observeDataFromViewModel()


        }

    }

    private fun observeDataFromViewModel() {
// prog , fail , done before m not  done
   viewModel.stopProgressBar.observe(this, Observer {
       if(it.equals("stop")){
           binding.progressbar.isVisible=false
       }

       viewModel.userFoundedGotBefore.observe(this, Observer {
           if(it==1){
               Log.e("observe", Thread.currentThread().name)

               showDialog("استحقاق الوجبة","هذا الطالب اخذ وجبته من قبل")
           }else if(it==-1){
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
                { dialogInterface, i ->
                    Log.e("dialog", Thread.currentThread().name)

                    Log.e("dailog","okkkkk")
                    dialogInterface.dismiss() })
                           .show()




    }

    private fun checkIfGetBeforeOrNot(sdn:String) {
        //get FromView Model
        Log.e("check fun", "")

        viewModel.getAllSdnDocuments(sdn)

    }

    // Launch
    private fun onScanBtnClick() {
        barcodeLauncher.launch(ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        })
    }

    private fun getResult(result: ScanIntentResult) {
        binding.progressbar.isVisible=true
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            //create and compare with the table
                var userSdn = result.contents
            Log.e("scaned", userSdn)
            Log.e("tested", Thread.currentThread().name)

            checkIfGetBeforeOrNot(userSdn)
            Toast.makeText(
                this,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()}
    }


    override fun showDialoge(message: String?, postAction: DialogInterface.OnClickListener,
                             neg: DialogInterface.OnClickListener?) {

        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(
            "طلب مسح الداتا الموجودة بالكامل:"
        )
        //set message for alert dialog
        builder.setMessage(message)
        //  builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("موافق",postAction)
        //performing cancel action
        if(neg!=null){
            builder.setNeutralButton("غير موافق",neg)}

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog propertienas
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    lateinit var progresDialog: ProgressDialog
    override fun showLodingDialog() {
        progresDialog = ProgressDialog(this)
        progresDialog?.setMessage("جاري التحميل...")
        progresDialog?.setCancelable(false)
        progresDialog?.show()
    }

    override fun hideLodingDialog() {
        progresDialog?.dismiss()
        // progresDialog=null
    }



}
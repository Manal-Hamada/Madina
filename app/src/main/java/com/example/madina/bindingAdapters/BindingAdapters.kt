package com.example.madinaapp.ui.bindingAdapters

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.madina.utils.Constants
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

@BindingAdapter(value = ["app:createQrc", "app:setImageView"])
  fun createQrcFromSnd(createBtn: Button,sdn : String , imageViewQrCode:ImageView) {

    if(Constants.createButtonIsEnabled==true){

       createBtn.setOnClickListener(View.OnClickListener {

             generateQrc(sdn, imageViewQrCode)
           disableCreateButton(createBtn,sdn)
       })

    }else{
        Log.e("binding ifenabled false",sdn)

        // post solve the plured issue
        imageViewQrCode.post(Runnable { generateQrc(Constants.sdn, imageViewQrCode) })

    } }



fun generateQrc(sdn:String, imageViewQrCode:ImageView) {

    imageViewQrCode.post(Runnable {  try {
        Log.e("binding adabter",sdn)

        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(
            sdn,
            BarcodeFormat.QR_CODE,
            imageViewQrCode.width,
            imageViewQrCode.height
        )
        imageViewQrCode.setImageBitmap(bitmap)


    } catch (e: Exception) {
    } })


}

@BindingAdapter("afterCreate:disapleButton")
fun disableCreateButton(createBtn: Button,sdn : String) {
           createBtn.isEnabled=false
    Constants.createButtonIsEnabled=false;


}

package com.example.madina.qrc

import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

@BindingAdapter(value = ["app:createQrc", "app:setImageView"])
fun createQrcFromSnd(createBtn: Button, sdn : String, imageViewQrCode: ImageView) {
    try {
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.encodeBitmap(
            sdn,
            BarcodeFormat.QR_CODE,
            imageViewQrCode.width,
            imageViewQrCode.height
        )
        imageViewQrCode.setImageBitmap(bitmap)
    } catch (e: Exception) {
    }

}
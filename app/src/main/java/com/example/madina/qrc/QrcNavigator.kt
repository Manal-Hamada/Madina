package com.example.madina.qrc

import android.content.DialogInterface

interface QrcNavigator {

    abstract fun showDialoge(
        message: String?,
        postAction: DialogInterface.OnClickListener,
        negAction: DialogInterface.OnClickListener?
    )

    abstract  fun showLodingDialog()

    abstract  fun hideLodingDialog()

}
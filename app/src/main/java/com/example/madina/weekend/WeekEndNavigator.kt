package com.example.madina.weekend

import android.content.DialogInterface
import com.example.madina.generated.callback.OnClickListener
import com.thecode.aestheticdialogs.OnDialogClickListener

interface WeekEndNavigator {
   abstract fun showDialoge(
       message: String?,
       postAction: DialogInterface.OnClickListener,
       negAction: DialogInterface.OnClickListener?
    )
    abstract fun controlProgressBar(b: Boolean)


}
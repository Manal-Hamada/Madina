package com.example.madina.login

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Bindingadapters {

    @BindingAdapter(value=["app:error"])
    fun setTextInputError(textInputLayout: EditText, error: String) {
        try {
            textInputLayout.error = error
        } catch(e: Exception){}
    }

}

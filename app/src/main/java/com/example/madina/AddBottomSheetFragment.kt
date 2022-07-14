package com.example.madina

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.madina.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddBottomSheetFragment: BottomSheetDialogFragment() {

    var date:String ?=null

     var _binding:BottomSheetBinding?=null
    val  binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetBinding.inflate(inflater,container,false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStartDatelistener()
        setEndDatelistener()
        setconBtnListener()



    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun showeDatePicker(myView: TextView){

        val myFormat ="dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val c= Calendar.getInstance()
        val view =myView

        val dataSetListener= object : DatePickerDialog.OnDateSetListener {

            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                myView.setText(sdf.format(c.time))
            }

        }

        val dataPicker= DatePickerDialog(requireContext(),dataSetListener,c.get(Calendar.YEAR),
            c.get( Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))

        dataPicker.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setStartDatelistener(){


      binding.startDate.setOnClickListener {
            showeDatePicker(binding.startDate)


        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setEndDatelistener(){

       binding.endDateCard.setOnClickListener {
            showeDatePicker(binding.endDate)
        }
    }
    fun setconBtnListener(){

        binding.dateConfBtn.setOnClickListener{

            this.dismiss()
        }



    }


}
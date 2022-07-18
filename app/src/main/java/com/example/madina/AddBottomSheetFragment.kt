package com.example.madina

import android.app.Application
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.madina.databinding.BottomSheetBinding
import com.example.madina.utils.setNegativeButton
import com.example.madina.utils.setPositiveButton
import com.example.madina.weekend.VacationModel

import com.example.madina.weekend.WeekEndNavigator
import com.example.madina.weekend.WeekEndViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thecode.aestheticdialogs.*
import java.text.SimpleDateFormat
import java.util.*

class AddBottomSheetFragment : BottomSheetDialogFragment(),WeekEndNavigator{

    var  date :String ?=null

     var _binding:BottomSheetBinding?=null
    val  binding get() =_binding!!
    lateinit var viewModel:WeekEndViewModel

    override fun onCreateView(
         inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState : Bundle?
    ): View? {
        _binding = BottomSheetBinding.inflate(inflater,container,false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel= ViewModelProvider(requireActivity()).get(WeekEndViewModel::class.java)
        viewModel.navigator=this
        binding.vm=viewModel
        setStartDatelistener()
        setEndDatelistener()
        setconBtnListener()



    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun showeDatePicker(dateTextView: TextView){

        val myFormat ="dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val c= Calendar.getInstance()
        val view =dateTextView

        val dataSetListener= object : DatePickerDialog.OnDateSetListener {

            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, month)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                dateTextView
                    .setText(sdf.format(c.time))
            }

        }

        val dataPicker= DatePickerDialog(requireContext(),dataSetListener,c.get(Calendar.YEAR),
            c.get( Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))
           dataPicker.datePicker.minDate=System.currentTimeMillis()-1000

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


    @RequiresApi(Build.VERSION_CODES.N)
    fun setconBtnListener(){

        binding.dateConfBtn.setOnClickListener{
             //val current : String = getNetworkDate()
            viewModel.createVacation()
           // this.dismiss()



        }



    }


    override fun showDialoge(message: String?, postAction: DialogInterface.OnClickListener,
                             neg: DialogInterface.OnClickListener?) {

        val builder = AlertDialog.Builder(requireContext())
        //set title for alert dialog
        builder.setTitle(
            "نتيجة الطلب المقدم للأجازة:"
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

    override fun controlProgressBar(b: Boolean) {
    }


    var progresDialog: ProgressDialog? = null
    override fun showLodingDialog() {
        progresDialog = ProgressDialog(requireActivity())
        progresDialog?.setMessage("جاري التحميل...")
        progresDialog?.setCancelable(false)
        progresDialog?.show()
    }

    override fun hideLodingDialog() {
        progresDialog?.dismiss()
        progresDialog=null
    }

    override fun dismmissBottomSheetFragment() {
        this.dismiss()
        viewModel.startDate.value=""
        viewModel.endDate.value=""
    }

    override fun listAllNeedsFromFirebase(list: MutableList<VacationModel>) {
    }
}




package com.example.madina.weekend

import android.icu.util.LocaleData
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.net.MailTo.parse
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.madina.AddBottomSheetFragment
import com.example.madina.database.getCollection
import com.example.madina.databinding.FragmentWeekendBinding
import com.example.madina.needs.NeedsAdapter
import com.example.madina.needs.NeedsModel
import com.google.android.gms.common.util.HttpUtils.parse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.Duration.parse
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Level.parse
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.parse

class WeekEndFragment:Fragment(),Navigator {
    private var _binding: FragmentWeekendBinding? = null
    private val binding get() = _binding!!
    var addButton: FloatingActionButton? = null
    var list= mutableListOf<WeekEndModel>()
     var pickedDate:String?=null

    lateinit var viewModel: WeekEndViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     viewModel=  ViewModelProvider(this).get(WeekEndViewModel::class.java)

        _binding = FragmentWeekendBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator=this
        addButton = _binding?.addBtn
       // pickDate()
        viewModel.getAllVacations()
        setAddBtnClickListener()


    }

    fun setAddBtnClickListener() {

        addButton?.setOnClickListener {

            showBottomSheet()
        }
    }


    fun showBottomSheet() {
        val bottomSheet = AddBottomSheetFragment().show(this.parentFragmentManager, "")

    }

    private fun initRecycler(vacations:MutableList<WeekEndModel>) {
        val adapter: WeekEndAdapter = WeekEndAdapter(vacations)
        _binding?.weekendRecycler?.adapter = adapter



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun pickDate():String? {

        val calendr: CalendarView = _binding!!.weekCalender
        calendr.setOnDateChangeListener(CalendarView.OnDateChangeListener { calendr, i, i2, i3 ->

           pickedDate= String.format("%d", i3) + "/" + String.format("%d", i2+1) + "/" + String.format( "%d", i )

            Log.e("date", pickedDate!!)
        })
       return pickedDate
    }

    override fun listAllVacationsFromFireBase(weekModel: MutableList<WeekEndModel>) {

        val selectedDate:String?=pickDate()

        for(t in weekModel) {
            if (t.startDate.equals(selectedDate)) {
                list.add(0, t)
                break
            }
        }
            if(list.size==0){
                var toast= Toast.makeText(activity," ther is No vacations",Toast.LENGTH_SHORT)
                toast.show()
                initRecycler(weekModel)
            }
           if(list.size==1)
                initRecycler(list)
        Log.e("list",list.size.toString())
        }


    }






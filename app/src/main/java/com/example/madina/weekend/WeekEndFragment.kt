package com.example.madina.weekend

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.madina.AddBottomSheetFragment
import com.example.madina.databinding.FragmentWeekendBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeekEndFragment:Fragment() {// WeekEndNavigator{
    private var _binding: FragmentWeekendBinding? = null
    private val binding get() = _binding!!
   lateinit var viewModel:WeekEndViewModel
    var addButton: FloatingActionButton?=null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

           viewModel= ViewModelProvider(this).get(WeekEndViewModel::class.java)
          //  viewModel.navigator=this
        _binding = FragmentWeekendBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButton = _binding?.addBtn
        setAddBtnClickListener()


    }
    fun setAddBtnClickListener() {

        addButton?.setOnClickListener {

            showBottomSheet()
        }
    }


    fun showBottomSheet() {
        val bottomSheet = AddBottomSheetFragment().show(this.parentFragmentManager,"")




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//
//    override fun showDialoge(message: String?, postAction: DialogInterface.OnClickListener,
//                             neg: DialogInterface.OnClickListener?) {
//
//        val builder = AlertDialog.Builder(requireActivity())
//        //set title for alert dialog
//        builder.setTitle(
//            "نتيجة الطلب المقدم للأجازة:"
//        )
//        //set message for alert dialog
//        builder.setMessage(message)
//        //  builder.setIcon(android.R.drawable.ic_dialog_alert)
//
//        //performing positive action
//        builder.setPositiveButton("موافق",postAction)
//        //performing cancel action
//        if(neg!=null){
//            builder.setNeutralButton("غير موافق",neg)}
//
//        // Create the AlertDialog
//        val alertDialog: AlertDialog = builder.create()
//        // Set other dialog propertienas
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//    }
//
//    override fun controlProgressBar(b: Boolean) {
//       // binding.progressCircular.isVisible=b
//    }
//   lateinit var progresDialog: ProgressDialog
//    override fun showLodingDialog() {
//        progresDialog = ProgressDialog(requireActivity())
//        progresDialog?.setMessage("جاري التحميل...")
//        progresDialog?.setCancelable(false)
//        progresDialog?.show()
//    }
//
//    override fun hideLodingDialog() {
//        progresDialog?.dismiss()
//       // progresDialog=null
//    }
//
//    override fun dismmissBottomSheetFragment() {
//
//    }
}
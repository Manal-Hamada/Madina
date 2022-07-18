package com.example.madina.needs

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.madina.R
import com.example.madina.databinding.FragmentNeedsBinding
import com.example.madina.utils.Constants.checkConnectionType
import com.example.madina.weekend.VacationModel
import com.example.madina.weekend.WeekEndNavigator
import com.example.madina.weekend.WeekEndViewModel

class NeedsFragment:Fragment() , WeekEndNavigator {
  lateinit var viewModel: WeekEndViewModel
    private var _binding: FragmentNeedsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       viewModel=
            ViewModelProvider(this).get(WeekEndViewModel::class.java)
            viewModel.navigator=this
        _binding = FragmentNeedsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
       // initRecycler()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(checkConnectionType(requireContext())==false){
            _binding!!.noDataOrInterntImage.setImageResource(R.drawable.wifioff)
        }else{
        viewModel.getAllNeedsAndTaxes()}

    }

    private fun initRecycler(needs:MutableList<VacationModel>) {


         if(needs.size==0){

              _binding!!.noDataTextView.setText("لا يوجد مستحقات")
          }else {
              val adapter : NeedsAdapter = NeedsAdapter(needs)
              _binding?.needsRecycler?.adapter=adapter
          }


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun listAllNeedsFromFirebase(list: MutableList<VacationModel>) {
      initRecycler(list)
    }


    override fun showDialoge(message: String?, postAction: DialogInterface.OnClickListener,
                             neg: DialogInterface.OnClickListener?) {

        val builder = AlertDialog.Builder(requireActivity())
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
       // binding.progressCircular.isVisible=b
    }
   lateinit var progresDialog: ProgressDialog
    override fun showLodingDialog() {
        progresDialog = ProgressDialog(requireActivity())
        progresDialog?.setMessage("جاري التحميل...")
        progresDialog?.setCancelable(false)
        progresDialog?.show()
    }

    override fun hideLodingDialog() {
        progresDialog?.dismiss()
       // progresDialog=null
    }

    override fun dismmissBottomSheetFragment() {

    }
}
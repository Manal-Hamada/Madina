package com.example.madina.qrc

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.madina.utils.Constants

import com.example.madina.databinding.FragmentQrcBinding
import com.example.madinaapp.ui.bindingAdapters.generateQrc


class QRCFragment : Fragment() {

    private var _fragmentBinding: FragmentQrcBinding? = null
    lateinit var qrcViewModel: QRCViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _fragmentBinding = FragmentQrcBinding.inflate(inflater, container, false)
        val root: View = _fragmentBinding!!.root

        return root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModelAndBindingVariables()
        //checkGenerateQrcBtnDis_Enb()
        generateQrc(Constants.userAotoId,_fragmentBinding!!.qrcImageview)

//
//        qrcViewModel.sdn.observe(viewLifecycleOwner, Observer {
//            Log.e("observe sdn in fragment",it)
//
//           //whithout this line and if i used the vm.sdn in xml the value of sdn in binding adapter not be changed
//            _fragmentBinding!!.sdnEditTextValue=it;
//            if(!it.equals("")){
//                Constants.sdn=it;
//
//            }
//        })

    }

//    private fun checkGenerateQrcBtnDis_Enb() {
////       if(Constants.createButtonIsEnabled==false){
////           _fragmentBinding?.createQrc?.isEnabled=false
////       }else{
////           _fragmentBinding?.createQrc?.isEnabled=true
////       }
//    }

    private fun initializeViewModelAndBindingVariables() {

        qrcViewModel  =ViewModelProvider(this).get(QRCViewModel::class.java)
        //_fragmentBinding!!.qrCodeViewModel=qrcViewModel

        //_fragmentBinding!!.qrCodeImageViewVariable=_fragmentBinding!!.qrcImageview
        Log.e("after set variables","")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
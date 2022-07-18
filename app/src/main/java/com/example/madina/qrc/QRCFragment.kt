package com.example.madina.qrc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madina.databinding.FragmentQrcBinding

class QRCFragment:Fragment() {
    private var _binding: FragmentQrcBinding? = null
    lateinit var fUser:String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
     lateinit var vieModel:QRCViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(QRCViewModel::class.java)

        _binding = FragmentQrcBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //  val textView: TextView = binding.textNotifications
        //  notificationsViewModel.text.observe(viewLifecycleOwner) {
        //      textView.text = it
        //  }
        return root
        _binding!!.qrCodeViewModel=vieModel

       // vieModel.createQrcFromSnd(_binding!!.createQrc, _binding!!.qrcSnd.text.toString(),_binding!!.qrcImageview)


    //not writtten in create btn in xml i delete it for not beeing an crash just try to handl
       // them without using binding adapers its causes crashes

        //app:createQrc="@{sdnEditTextValue}"
        //app:setImageView="@{qrCodeImageViewVariable}"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
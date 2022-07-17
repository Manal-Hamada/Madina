package com.example.madina.weekend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.madina.AddBottomSheetFragment
import com.example.madina.databinding.FragmentWeekendBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeekEndFragment:Fragment() {
    private var _binding: FragmentWeekendBinding? = null
    private val binding get() = _binding!!

    var addButton: FloatingActionButton?=null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(WeekEndViewModel::class.java)

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
}
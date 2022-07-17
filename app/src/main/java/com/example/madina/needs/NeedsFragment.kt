package com.example.madina.needs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.madina.databinding.FragmentNeedsBinding

class NeedsFragment:Fragment() {
    private var _binding: FragmentNeedsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(NeedsViewModel::class.java)

        _binding = FragmentNeedsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
        initRecycler()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val adapter : NeedsAdapter = NeedsAdapter(getListOfNeeds())
        _binding?.needsRecycler?.adapter=adapter


    }

    private fun getListOfNeeds(): List<NeedsModel> {
        val needs = listOf(
            NeedsModel(
                "13/3/2022"
                ,"15/3/2020"
                ,"50 جنيه"
            ),
            NeedsModel(
                "13/3/2022"
                ,"15/3/2020"
                ,"50 جنيه"
            ),  NeedsModel(
                "13/3/2022"
                ,"15/3/2020"
                ,"50 جنيه"
            ),  NeedsModel(
                "13/3/2022"
                ,"15/3/2020"
                ,"50 جنيه"
            ),  NeedsModel(
                "13/3/2022"
                ,"15/3/2020"
                ,"50 جنيه"
            ))
        return  needs

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
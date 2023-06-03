package com.sayursehat.paterai.ui.market.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sayursehat.paterai.adapter.ListHomeProductAdapter
import com.sayursehat.paterai.databinding.FragmentHomeMarketBinding
import com.sayursehat.paterai.model.InitialDataDummy
import com.sayursehat.paterai.model.Vegetable

class HomeMarketFragment : Fragment() {

    private var _binding: FragmentHomeMarketBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeMarketBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        setupView()
    }

    private fun setupView(){
        val layoutManager = GridLayoutManager(activity, 2)
        binding?.rvHomeProduct?.layoutManager = layoutManager
        setListProductData(InitialDataDummy.getVegetables())
    }

    private fun setListProductData(listProduct: List<Vegetable>){
        val adapter = ListHomeProductAdapter(listProduct)
        binding?.rvHomeProduct?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
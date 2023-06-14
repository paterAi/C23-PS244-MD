package com.sayursehat.paterai.ui.market.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sayursehat.paterai.adapter.ListProductStoreAdapter
import com.sayursehat.paterai.databinding.FragmentProductMarketBinding
import com.sayursehat.paterai.model.InitialDataDummy
import com.sayursehat.paterai.model.Product

class ProductMarketFragment : Fragment() {
    private var _binding: FragmentProductMarketBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductMarketBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        setupView()
    }

    private fun setupView(){
        val layoutManager = LinearLayoutManager(activity)
        binding?.rvProduct?.layoutManager = layoutManager
        setListProductData(InitialDataDummy.getProducts())
    }

    private fun setListProductData(listProduct: List<Product>){
        val adapter = ListProductStoreAdapter(listProduct)
        binding?.rvProduct?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
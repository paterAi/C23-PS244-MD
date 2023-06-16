package com.sayursehat.paterai.ui.market.product

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.sayursehat.paterai.R
import com.sayursehat.paterai.adapter.ListHomeProductAdapter
import com.sayursehat.paterai.databinding.FragmentHomeMarketBinding
import com.sayursehat.paterai.databinding.FragmentProductMarketBinding
import com.sayursehat.paterai.model.Vegetable
import com.sayursehat.paterai.ui.market.product.detail.DetailProductMarketActivity

class ProductMarketFragment : Fragment() {

    private var _binding: FragmentProductMarketBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var data: List<Vegetable> = listOf()
    private var dataFilter: List<Vegetable> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductMarketBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
        setupView()
    }

    private fun setupView() {
        val layoutManager = GridLayoutManager(activity, 2)
        binding?.apply {
            rvProduct.layoutManager = layoutManager
            edtProduct.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(c: CharSequence, p1: Int, p2: Int, p3: Int) {
                    dataFilter = data.filter {
                        it.name?.contains(c, ignoreCase = true) ?: false
                    }
                    setListProductData(dataFilter)
                }

                override fun afterTextChanged(p0: Editable?) {

                }


            })
        }

        db.collection("vegetables")
            .get()
            .addOnSuccessListener { result ->
                val listVegetable = arrayListOf<Vegetable>()
                for (document in result) {
                    val data = document.toObject<Vegetable>()
                    listVegetable.add(data)
                }
                data = listVegetable
                setListProductData(data)
            }
    }

    private fun setListProductData(listProduct: List<Vegetable>) {
        val adapter = ListHomeProductAdapter(listProduct, onClick = {
            val intent = Intent(requireActivity(), DetailProductMarketActivity::class.java)
            intent.putExtra(DetailProductMarketActivity.EXTRA_VEGETABLE, it)
            startActivity(intent)
        })
        binding?.rvProduct?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
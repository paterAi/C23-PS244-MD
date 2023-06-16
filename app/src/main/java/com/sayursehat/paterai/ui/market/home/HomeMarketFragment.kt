package com.sayursehat.paterai.ui.market.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.sayursehat.paterai.adapter.ListHomeProductAdapter
import com.sayursehat.paterai.databinding.FragmentHomeMarketBinding
import com.sayursehat.paterai.model.Cart
import com.sayursehat.paterai.model.Product
import com.sayursehat.paterai.model.User
import com.sayursehat.paterai.model.Vegetable
import com.sayursehat.paterai.ui.market.maps.MapsActivity
import com.sayursehat.paterai.ui.market.product.detail.DetailProductMarketActivity

class HomeMarketFragment : Fragment() {

    private var _binding: FragmentHomeMarketBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var cart: Cart = Cart()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeMarketBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore

        setupView()
        setupAction()
    }

    private fun setupView() {
        val currentUser = auth.currentUser
        val layoutManager = GridLayoutManager(activity, 2)
        binding?.apply {
            tvHomeNameUser.text = currentUser?.displayName
            Glide.with(requireActivity()).load(currentUser?.photoUrl)
                .circleCrop()
                .into(imgHomeUser)
            rvHomeProduct.layoutManager = layoutManager
        }

        getUserInfo()

        db.collection("vegetables")
            .limit(4)
            .get()
            .addOnSuccessListener { result ->
                val listVegetable = arrayListOf<Vegetable>()
                for (document in result) {
                    val data = document.toObject<Vegetable>()
                    listVegetable.add(data)
                }
                setListProductData(listVegetable)
            }
            .addOnFailureListener { exception ->

            }
    }

    private fun setupAction() {
        binding?.tvHomeLocation?.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setListProductData(listProduct: List<Vegetable>) {
        val adapter = ListHomeProductAdapter(listProduct, onClick = {
            val intent = Intent(requireActivity(), DetailProductMarketActivity::class.java)
            intent.putExtra(DetailProductMarketActivity.EXTRA_VEGETABLE, it)
            startActivity(intent)
        })
        binding?.rvHomeProduct?.adapter = adapter
    }

    private fun getUserInfo() {
        db.collection("users")
            .whereEqualTo(FieldPath.documentId(), auth.uid)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    cart = document.toObject<User>().cart
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.sayursehat.paterai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sayursehat.paterai.databinding.ItemMarketHomeBinding
import com.sayursehat.paterai.model.Vegetable
import com.sayursehat.paterai.utils.Utils

class ListHomeProductAdapter(
    private val listProduct: List<Vegetable>,
    private val onClick: (Vegetable) -> Unit = {}
) :
    RecyclerView.Adapter<ListHomeProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMarketHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(listProduct[position].photoUrl)
                .into(imgVegetable)
            tvNameVegetable.text = listProduct[position].name
            tvPriceVegetable.text = listProduct[position].price?.let {
                Utils.convertToIDRFormat(it)
            }
            tvWeightVegetable.text = listProduct[position].weight
            btnAddProduct.setOnClickListener {
                onClick(listProduct[position])
            }
        }

    }

    override fun getItemCount() = listProduct.size

    inner class ViewHolder(var binding: ItemMarketHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

}
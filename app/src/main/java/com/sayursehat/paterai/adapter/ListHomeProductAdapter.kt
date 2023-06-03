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
    private val listProduct: List<Vegetable>
) :
    RecyclerView.Adapter<ListHomeProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMarketHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            imgVegetable.setImageResource(listProduct[position].image)
            tvNameVegetable.text = listProduct[position].name
            tvPriceVegetable.text = Utils.convertToIDRFormat(listProduct[position].price)
        }

    }

    override fun getItemCount() = listProduct.size

    inner class ViewHolder(var binding: ItemMarketHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

}
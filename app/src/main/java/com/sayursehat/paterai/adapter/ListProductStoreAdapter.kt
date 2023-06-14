package com.sayursehat.paterai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sayursehat.paterai.databinding.ItemMarketProductBinding
import com.sayursehat.paterai.model.Product
import com.sayursehat.paterai.utils.Utils

class ListProductStoreAdapter(
    private val listProduct: List<Product>
) :
    RecyclerView.Adapter<ListProductStoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMarketProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            imgVegetable.setImageResource(listProduct[position].image)
            tvNameVegetable.text = listProduct[position].name
            tvPriceRangeVegetable.text = Utils.convertPriceWithRange(listProduct[position].priceLowest, listProduct[position].priceHighest)

        }

    }

    override fun getItemCount() = listProduct.size

    inner class ViewHolder(var binding: ItemMarketProductBinding) :
        RecyclerView.ViewHolder(binding.root)

}
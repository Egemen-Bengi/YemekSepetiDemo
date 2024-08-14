package com.egemenbengi.yemeksepetidemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.egemenbengi.yemeksepetidemo.databinding.FoodsRowBinding
import com.egemenbengi.yemeksepetidemo.model.FoodsModel
import com.egemenbengi.yemeksepetidemo.model.sepetSingleton
import com.egemenbengi.yemeksepetidemo.view.SelectedFoods

class FoodsRowAdapter(val foodsList: ArrayList<FoodsModel>): RecyclerView.Adapter<FoodsRowAdapter.FoodsViewHolder>() {
     class FoodsViewHolder(val binding: FoodsRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val binding = FoodsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        holder.binding.textFoodsRowID.text = foodsList[position].namef.plus(" "+foodsList[position].price)
        holder.binding.btnSepeteEkle.setOnClickListener {
            sepetSingleton.sepetFoods.add(foodsList[position])
        }
    }
}
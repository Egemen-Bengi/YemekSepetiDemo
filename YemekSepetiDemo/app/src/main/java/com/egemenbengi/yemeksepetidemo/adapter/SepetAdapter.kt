package com.egemenbengi.yemeksepetidemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egemenbengi.yemeksepetidemo.databinding.SepetRowBinding
import com.egemenbengi.yemeksepetidemo.model.FoodsModel
import com.egemenbengi.yemeksepetidemo.model.sepetSingleton

class SepetAdapter(val sepetList: ArrayList<FoodsModel>): RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {
    class SepetViewHolder(val binding: SepetRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val binding = SepetRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SepetViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sepetList.size
    }

    fun updateSepetList(newList: ArrayList<FoodsModel>){
        sepetList.clear()
        sepetList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        holder.binding.textSepetRowID.text = sepetList[position].namef.plus(" "+sepetList[position].price)
        holder.binding.btnnDeleteID.setOnClickListener {
            sepetSingleton.sepetFoods.remove(sepetList[position])
            notifyDataSetChanged()
        }
    }
}
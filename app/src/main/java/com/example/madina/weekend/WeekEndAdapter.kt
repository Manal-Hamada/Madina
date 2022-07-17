package com.example.madina.weekend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.madina.R
import com.example.madina.databinding.NeedsRecyclerItemBinding
import com.example.madina.databinding.WeekendRecyclerItemBinding
import com.example.madina.needs.NeedsAdapter
import com.example.madina.needs.NeedsModel

class WeekEndAdapter(val allvacations:List<WeekEndModel>): RecyclerView.Adapter<WeekEndAdapter.WeekEndViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekEndAdapter.WeekEndViewHolder {

        var item : WeekendRecyclerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.needs_recycler_item,parent, false
        )
        return WeekEndAdapter.WeekEndViewHolder(item)
    }

    override fun onBindViewHolder(holder: WeekEndAdapter.WeekEndViewHolder, position: Int) {
        val model=allvacations[position]

        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return allvacations.size
    }

    class WeekEndViewHolder(val item : WeekendRecyclerItemBinding):RecyclerView.
    ViewHolder(item.root) {
        fun bind(weekendModel: WeekEndModel) {
            item.model = weekendModel
            item.executePendingBindings()
        }
    }

}
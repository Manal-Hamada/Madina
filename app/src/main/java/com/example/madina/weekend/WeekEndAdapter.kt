package com.example.madina.weekend

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.madina.R
import com.example.madina.databinding.WeekendRecyclerItemBinding


class WeekEndAdapter(val allvacations:MutableList<WeekEndModel>): RecyclerView.Adapter<WeekEndAdapter.WeekEndViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekEndAdapter.WeekEndViewHolder {

        val item : WeekendRecyclerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.weekend_recycler_item,parent, false
        )
        return WeekEndAdapter.WeekEndViewHolder(item)
    }

    override fun onBindViewHolder(holder: WeekEndAdapter.WeekEndViewHolder, position: Int) {
        val model=allvacations[position]

           holder.bind(model)

    }

    override fun getItemCount(): Int {
      //  return allvacations.size
        return allvacations.size
    }

    class WeekEndViewHolder(val item : WeekendRecyclerItemBinding):RecyclerView.
    ViewHolder(item.root) {
        fun bind(weekendModel: WeekEndModel?) {
            item.model = weekendModel
            item.executePendingBindings()
        }
    }



}
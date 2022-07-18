package com.example.madina.needs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.madina.R
import com.example.madina.databinding.NeedsRecyclerItemBinding
import com.example.madina.weekend.VacationModel

class NeedsAdapter (val allNeeds:List<VacationModel>): RecyclerView.Adapter<NeedsAdapter.NeedsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeedsViewHolder {
        var item : NeedsRecyclerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.needs_recycler_item,parent, false
        )
        return NeedsViewHolder(item)

    }

    override fun onBindViewHolder(holder: NeedsViewHolder, position: Int) {
        val model= allNeeds[position]

        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return allNeeds.size
    }

    class NeedsViewHolder(val item : NeedsRecyclerItemBinding):RecyclerView.
    ViewHolder(item.root) {
        fun bind(needsmodel: VacationModel) {
            item.model = needsmodel
            item.executePendingBindings()
        }
    }
}
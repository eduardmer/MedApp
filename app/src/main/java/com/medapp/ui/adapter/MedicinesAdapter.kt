package com.medapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.medapp.data.local.database.MedicineEntity
import com.medapp.databinding.ViewMedicinesItemBinding

class MedicinesAdapter(private val context: Context, private val listener: OnItemClickListener) : ListAdapter<MedicineEntity, MedicinesAdapter.MyViewHolder>(DiffCallBack()) {

    class MyViewHolder(private val binding: ViewMedicinesItemBinding, private val listener: OnItemClickListener) : ViewHolder(binding.root) {
        fun bind(item: MedicineEntity) {
            binding.medicine = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewMedicinesItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallBack : DiffUtil.ItemCallback<MedicineEntity>() {
        override fun areItemsTheSame(oldItem: MedicineEntity, newItem: MedicineEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MedicineEntity, newItem: MedicineEntity): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: MedicineEntity)
    }

}
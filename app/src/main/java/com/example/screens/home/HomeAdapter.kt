package com.example.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.ListItemFoodsBinding
import com.example.nbt_domain.models.Food
import timber.log.Timber

class HomeAdapter : ListAdapter<Food, HomeAdapter.FoodViewHolder>(FoodDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class FoodViewHolder private constructor(private val binding: ListItemFoodsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food) {
            binding.food = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FoodViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFoodsBinding.inflate(layoutInflater, parent, false)
                return FoodViewHolder(binding)
            }
        }
    }
}

class FoodDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}
package com.example.screens.home.foods_viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.databinding.ListItemFoodsBinding
import com.example.nbt_domain.models.Food
import com.google.android.material.chip.Chip

class FoodListAdapter(private val clickListener: FoodListListener) :
    ListAdapter<Food, FoodListAdapter.FoodViewHolder>(FoodDiffCallback()) {

    private val selectedSet = mutableSetOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder.from(parent, selectedSet)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class FoodViewHolder private constructor(
        private val binding: ListItemFoodsBinding,
        private val selectedSet: MutableSet<String>
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food, clickListener: FoodListListener) {
            binding.food = item
            binding.executePendingBindings()

            binding.addBtn.isChecked = (selectedSet.contains(item.id))
            binding.addBtn.setOnClickListener { v ->
                val state = (v as Chip).isChecked
                clickListener.onClick(item, state)
                when (state) {
                    true -> {
                        v.text = v.context.getString(R.string.text_added_1)
                        selectedSet.add(item.id)
                    }
                    false -> {
                        v.text = v.context.getString(R.string.text_50_usd)
                        selectedSet.remove(item.id)
                    }
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, selectedSet: MutableSet<String>): FoodViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFoodsBinding.inflate(layoutInflater, parent, false)
                return FoodViewHolder(binding, selectedSet)
            }
        }
    }
}

class FoodListListener(val clickListener: (food: Food, isAdded: Boolean) -> Unit) {
    fun onClick(food: Food, isAdded: Boolean) = clickListener(food, isAdded)
}


class FoodDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}
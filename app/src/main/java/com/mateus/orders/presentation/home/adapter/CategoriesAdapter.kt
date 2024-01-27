package com.mateus.orders.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mateus.orders.databinding.CategoryItemBinding
import com.mateus.orders.domain.model.Category

class CategoriesAdapter(
    private val dataSet: List<Category>
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder(
        binding: CategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val categoryTitleTextview: TextView
        init {
            categoryTitleTextview = binding.categoryTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTitleTextview.text = dataSet[position].name
    }
}
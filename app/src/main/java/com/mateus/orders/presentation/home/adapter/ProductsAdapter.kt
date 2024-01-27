package com.mateus.orders.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mateus.orders.databinding.ProductItemBinding
import com.mateus.orders.domain.model.Product

class ProductsAdapter(private val dataSet: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    class ViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val productTitle: TextView
        val productDescription: TextView
        val productPrice: TextView

        init {
            productTitle = binding.productItemTitle
            productDescription = binding.productItemDescription
            productPrice = binding.productItemPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productTitle.text = dataSet[position].name
        holder.productDescription.text = dataSet[position].name
        holder.productPrice.text = dataSet[position].price.toString()
    }
}
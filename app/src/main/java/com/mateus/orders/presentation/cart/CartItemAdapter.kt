package com.mateus.orders.presentation.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.databinding.ProductCartItemBinding

class CartItemAdapter(
    private val dataSet: List<CartItem>,
    private val addProduct: (productId: Int) -> Unit,
    private val removeProduct: (productId: Int) -> Unit
) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {
    class ViewHolder(binding: ProductCartItemBinding) : RecyclerView.ViewHolder(binding.root){
        val title: TextView
        val description: TextView
        val price: TextView
        val quantity: TextView
        val plusButton: Button
        val minusButton: Button

        init {
            title = binding.productCartItemTitle
            description = binding.productCartItemDescription
            price = binding.productCartItemPrice
            quantity = binding.productCartItemQuantity
            plusButton = binding.productCartItemPlusButton
            minusButton = binding.productCartItemMinusButton
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductCartItemBinding.inflate(inflater, parent, false)
        val holder = ViewHolder(binding)

        holder.plusButton.setOnClickListener {
            val productId = it.tag as Int
            Log.d("aaa", "adding product button")
            addProduct(productId)
        }

        return holder
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSet[position].name
        holder.description.text = dataSet[position].description
        holder.quantity.text = dataSet[position].quantity.toString()
        holder.price.text = dataSet[position].price

        holder.plusButton.tag = dataSet[position].id

        holder.minusButton.setOnClickListener {
            removeProduct(dataSet[position].id)
        }
    }
}
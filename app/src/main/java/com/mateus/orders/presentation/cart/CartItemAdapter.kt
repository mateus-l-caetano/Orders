package com.mateus.orders.presentation.cart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.databinding.ProductCartItemBinding

class CartItemAdapter(
    private val addProduct: (productId: Int) -> Unit,
    private val removeProduct: (productId: Int) -> Unit
) : ListAdapter<CartItem, CartItemAdapter.ViewHolder>(CartItemDiffCallback()) {

    class ViewHolder(binding: ProductCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.productCartItemTitle
        val description: TextView = binding.productCartItemDescription
        val price: TextView = binding.productCartItemPrice
        val quantity: TextView = binding.productCartItemQuantity
        val plusButton: Button = binding.productCartItemPlusButton
        val minusButton: Button = binding.productCartItemMinusButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductCartItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = getItem(position)

        holder.title.text = cartItem.name
        holder.description.text = cartItem.description
        holder.quantity.text = cartItem.quantity.toString()
        holder.price.text = cartItem.price

        holder.plusButton.tag = cartItem.id
        holder.plusButton.setOnClickListener {
            Log.d("aaa", "adding product button")
            addProduct(cartItem.id)
        }

        holder.minusButton.setOnClickListener {
            removeProduct(cartItem.id)
        }
    }

    class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
            oldItem == newItem
    }
}

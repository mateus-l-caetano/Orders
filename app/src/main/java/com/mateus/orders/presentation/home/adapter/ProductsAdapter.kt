package com.mateus.orders.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.mateus.orders.databinding.ProductItemBinding
import com.mateus.orders.domain.model.Product
import com.mateus.orders.presentation.home.HomeFragmentDirections

class ProductsAdapter(
    private val dataSet: List<Product>,
    private val onAddButtonClick: (productId: Int) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    class ViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val productTitle: TextView
        val productDescription: TextView
        val productPrice: TextView
        val productImage: ImageView
        val productAddButton: Button
        val productItemCard: CardView

        init {
            productTitle = binding.productItemTitle
            productDescription = binding.productItemDescription
            productPrice = binding.productItemPrice
            productImage = binding.productImage
            productAddButton = binding.productItemButton
            productItemCard = binding.productItemCard
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        val holder = ViewHolder(binding)

        holder.productAddButton.setOnClickListener {
            val productId = it.tag as Int
            onAddButtonClick(productId)
        }

        return holder
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productTitle.text = dataSet[position].name
        holder.productDescription.text = dataSet[position].description
        holder.productPrice.text = dataSet[position].price.toString()
        holder.productImage.transitionName = "shared_element${dataSet[position].id}"

        holder.productAddButton.tag = dataSet[position].id

        holder.productItemCard.apply {
            isClickable = true

            val action = HomeFragmentDirections
                .actionHomeFragmentToProductDetailFragment(dataSet[position].id)

            setOnClickListener { card ->
                val extras = FragmentNavigatorExtras(holder.productImage to "shared_element_detail")

                card.findNavController().navigate(action, extras)
            }
        }
    }
}
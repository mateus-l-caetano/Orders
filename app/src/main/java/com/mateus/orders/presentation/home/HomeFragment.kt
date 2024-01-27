package com.mateus.orders.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mateus.orders.R
import com.mateus.orders.databinding.FragmentHomeBinding
import com.mateus.orders.domain.model.Category
import com.mateus.orders.domain.model.Product
import com.mateus.orders.presentation.home.adapter.CategoriesAdapter
import com.mateus.orders.presentation.home.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.math.BigDecimal

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val productsDataSet = mutableListOf<Product>()
        val categoriesDataSet = mutableListOf<Category>()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getProducts().collect { products ->
                    products.forEach { product ->
                        Log.d("home", product.name)
                        productsDataSet.add(product)

                        val productsAdapter = ProductsAdapter(productsDataSet.toList())
                        val categoriesAdapter = CategoriesAdapter(categoriesDataSet.toList())

                        val productsRecyclerView = binding.productsRecyclerview
                        val categoriesRecyclerView = binding.categoriesRecyclerview

                        productsRecyclerView.adapter = productsAdapter
                        categoriesRecyclerView.adapter = categoriesAdapter
                    }
                }
            }

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
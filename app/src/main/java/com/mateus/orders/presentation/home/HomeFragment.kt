package com.mateus.orders.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.mateus.orders.databinding.FragmentHomeBinding
import com.mateus.orders.domain.model.Category
import com.mateus.orders.domain.model.Product
import com.mateus.orders.presentation.home.adapter.CategoriesAdapter
import com.mateus.orders.presentation.home.adapter.ProductsAdapter
import com.mateus.orders.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale( true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val productsDataSet = mutableListOf<Product>()
        val categoriesDataSet = mutableListOf<Category>()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    getCategories(categoriesDataSet)
                }

                launch {
                    getProducts(productsDataSet)
                }

            }
        }

        return view
    }

    private suspend fun getCategories(categoriesDataSet: MutableList<Category>) {
        viewModel.getCategories().collect { categories ->
            when(categories) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {
                    categoriesDataSet.clear()
                    categoriesDataSet.addAll(categories.data.orEmpty())

                    val categoriesAdapter = CategoriesAdapter(
                        categoriesDataSet.toList()
                    ) { isSelected, categoryId ->
                        if (!isSelected)
                            viewModel.removeCategoriesFilter(categoryId)
                        else
                            viewModel.addCategoriesFilter(categoryId)
                    }

                    val categoriesRecyclerView = binding.successHomeLayout.categoriesRecyclerview
                    categoriesRecyclerView.adapter = categoriesAdapter
                }
            }
        }
    }

    private suspend fun getProducts(productsDataSet: MutableList<Product>) {
        viewModel.getProducts().collect { products ->
            when(products) {
                is Resource.Error -> {
                    binding.homeFlipper.displayedChild = 2
                }
                is Resource.Loading -> {
                    binding.homeFlipper.displayedChild = 1
                }
                is Resource.Success -> {
                    binding.homeFlipper.displayedChild = 0
                    productsDataSet.clear()
                    productsDataSet.addAll(products.data.orEmpty())

                    val productsAdapter = ProductsAdapter(productsDataSet.toList())
                    val productsRecyclerView = binding.successHomeLayout.productsRecyclerview
                    productsRecyclerView.adapter = productsAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.mateus.orders.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
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

                launch {
                    getCurrentOrder()
                }

                viewModel.loadOrder()
            }
        }

        return view
    }

    private suspend fun getCurrentOrder() {
        viewModel.getCurrentOrderId().collect { orderIdResource ->
            when (orderIdResource) {
                is Resource.Success -> {
                    val orderId: Int = orderIdResource.data ?: 0
                    Toast.makeText(context, "Pronto para adicionar itens ao pedido", Toast.LENGTH_SHORT).show()
                    binding.successHomeLayout.homeTopAppBar.setOnMenuItemClickListener {
                        val action = HomeFragmentDirections.actionHomeFragmentToCartFragment(orderId)
                        view?.findNavController()?.navigate(action)
                        true
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(context, "${orderIdResource.message}", Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {}
            }
        }
    }

    private suspend fun getCategories(categoriesDataSet: MutableList<Category>) {
        viewModel.getCategories().collect { categories ->
            when(categories) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    Toast.makeText(context, "${categories.message}", Toast.LENGTH_LONG).show()
                }
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

                    val productsAdapter = ProductsAdapter(productsDataSet.toList()) { productId ->
                        viewModel.addOrderItem(productId)

                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.getAddOrderItemState().collect { wasProductAdded ->
                                when (wasProductAdded) {
                                    is Resource.Error -> {
                                        Toast.makeText(context, "${wasProductAdded.message}", Toast.LENGTH_LONG).show()
                                    }
                                    else -> {}
                                }
                            }
                        }
                    }

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
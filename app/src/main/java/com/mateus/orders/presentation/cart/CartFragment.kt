package com.mateus.orders.presentation.cart

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
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.databinding.FragmentCartBinding
import com.mateus.orders.presentation.cart.CartItemAdapter.*
import com.mateus.orders.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CartViewModel by viewModels()
    private val args: CartFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.cartTopAppBar.setNavigationOnClickListener {
            it.findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderId = args.orderId

                launch {
                    viewModel.loadCartItems()
                }

                launch {
                    getCart()
                }
            }
        }

        return binding.root
    }

    private suspend fun getCart() {
        val cartItemsDataSet = mutableListOf<CartItem>()
        val cartRecyclerView = binding.cartRecyclerview

        val cartAdapter = CartItemAdapter(
            { productId ->
                viewModel.addProduct(productId)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getAddOrderItemState().collect{ state ->
                        when(state) {
                            is Resource.Success -> {
                                Log.d("aaa", "adicionado com sucesso")
                                launch { viewModel.loadCartItems() }
                            }

                            is Resource.Error -> {
                                Log.d("aaa", "deu ruim")
                            }
                            is Resource.Loading -> {
                                Log.d("aaa", "carregando")
                            }
                        }
                    }
                }
            },
            { productId ->
                viewModel.removeProduct(productId)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getRemoveOrderItemState().collect { state ->
                        when(state) {
                            is Resource.Success -> {
                                launch { viewModel.loadCartItems() }
                            }

                            else -> {}
                        }
                    }
                }
            }
        )

        cartRecyclerView.adapter = cartAdapter

        viewModel.getCartItems().collect { cartItemsListResource ->
            Log.d("aaa", "cart changed")
            when (cartItemsListResource) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Log.d("aaa", "cart changed success")
                    cartItemsDataSet.clear()
                    cartItemsDataSet.addAll(cartItemsListResource.data.orEmpty())
                    cartItemsDataSet.forEach{ item ->
                        Log.d("aaa", "cart product ${item.name} count ${item.quantity}")
                    }

                    val layoutManager = binding.cartRecyclerview.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val topOffset = layoutManager.findViewByPosition(firstVisibleItemPosition)?.top ?: 0

                    cartAdapter.submitList(cartItemsDataSet)
                    cartAdapter.notifyDataSetChanged()

                    layoutManager.scrollToPositionWithOffset(firstVisibleItemPosition, topOffset)
                }
            }
        }
    }
}
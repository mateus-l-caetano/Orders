package com.mateus.orders.presentation.cart

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mateus.orders.data.local.entity.CartItem
import com.mateus.orders.databinding.FragmentCartBinding
import com.mateus.orders.utils.CurrencyUtils
import com.mateus.orders.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.math.BigDecimal

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

        binding.cartSendOrderButton.setOnClickListener {
            viewModel.sendOrder()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getSendOrderState().collect { state ->
                    when(state) {
                        is Resource.Error -> {
                            Toast.makeText(context, "Erro ao enviar o pedido: ${state.message}", Toast.LENGTH_LONG).show()
                            it.findNavController().popBackStack()
                        }
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            Toast.makeText(context, "Pedido enviado com sucesso", Toast.LENGTH_LONG).show()
                            it.findNavController().popBackStack()
                        }
                    }
                }
            }
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

                    val itemsPrices = mutableListOf<BigDecimal>()
                    cartItemsDataSet.forEach {
                        itemsPrices.add(
                            BigDecimal(it.price)
                                .multiply(BigDecimal(it.quantity))
                        )
                    }

                    if(!itemsPrices.isNullOrEmpty()){
                        val totalValue = itemsPrices.reduce { sum, totalItem -> sum.plus(totalItem) }
                        binding.cartTotal.text = CurrencyUtils.calcCurrencyFromBigDecimal(totalValue, 1)
                    } else {
                        binding.cartTotal.text = CurrencyUtils.calcCurrencyFromBigDecimal(BigDecimal(0), 1)
                    }

                    layoutManager.scrollToPositionWithOffset(firstVisibleItemPosition, topOffset)
                }
            }
        }
    }
}
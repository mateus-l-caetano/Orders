package com.mateus.orders.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateus.orders.domain.model.Category
import com.mateus.orders.domain.model.Product
import com.mateus.orders.domain.use_case.list_categories.IListCategoriesUseCase
import com.mateus.orders.domain.use_case.list_products.IListProductsUseCase
import com.mateus.orders.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listCategoriesUseCase: IListCategoriesUseCase,
    private val listProductsUseCase: IListProductsUseCase
) : ViewModel() {
    private val categories = MutableStateFlow(listOf<Category>())
    private val products = MutableStateFlow(listOf<Product>())
    private val categoriesToFilter = mutableListOf<Int>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                loadCategories()
            }
            launch {
                loadProducts()
            }
        }
    }

    fun addCategoriesFilter(category: Int) {
        categoriesToFilter.add(category)
        viewModelScope.launch(Dispatchers.IO) {
            loadProducts()
        }
    }

    fun removeCategoriesFilter(category: Int) {
        categoriesToFilter.remove(category)
        viewModelScope.launch(Dispatchers.IO) {
            loadProducts()
        }
    }

    fun getCategories(): Flow<List<Category>> {
        return categories
    }

    fun getProducts() : Flow<List<Product>> {
        return products
    }

    private suspend fun loadCategories() {
        listCategoriesUseCase().collect { categoriesFlow ->
            when(categoriesFlow) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    categories.value = categoriesFlow.data.orEmpty()
                }
                is Resource.Error -> {}
            }
        }
    }

    private suspend fun loadProducts() {
        listProductsUseCase(categoriesToFilter).collect { productsFlow ->
            when(productsFlow) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    products.value = productsFlow.data.orEmpty()
                }
                is Resource.Error -> {
                }
            }
        }
    }
}
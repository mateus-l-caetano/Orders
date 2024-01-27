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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listCategoriesUseCase: IListCategoriesUseCase,
    private val listProductsUseCase: IListProductsUseCase
) : ViewModel() {
    private val categories: MutableStateFlow<Resource<List<Category>>> = MutableStateFlow(Resource.Loading())
    private val products: MutableStateFlow<Resource<List<Product>>> = MutableStateFlow(Resource.Loading())
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

    fun getCategories(): StateFlow<Resource<List<Category>>> {
        return categories.asStateFlow()
    }

    fun getProducts() : StateFlow<Resource<List<Product>>> {
        return products.asStateFlow()
    }

    private suspend fun loadCategories() {
        listCategoriesUseCase().collect { categoriesFlow ->
            categories.value = categoriesFlow
        }
    }

    private suspend fun loadProducts() {
        listProductsUseCase(categoriesToFilter).collect { productsFlow ->
            products.value = productsFlow
        }
    }
}
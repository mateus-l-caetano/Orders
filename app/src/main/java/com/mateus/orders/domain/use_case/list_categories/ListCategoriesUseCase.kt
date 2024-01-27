package com.mateus.orders.domain.use_case.list_categories

import com.mateus.orders.domain.model.Category
import com.mateus.orders.domain.repository.ICategoryRepository
import com.mateus.orders.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListCategoriesUseCase @Inject constructor(
    private val repository: ICategoryRepository
) : IListCategoriesUseCase {
    override fun invoke(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        try {
            repository.getCategories().collect { categoriesList ->
                emit(Resource.Success(categoriesList))
            }
        } catch (e: Exception) {
            emit(Resource.Error(
                message = e.message ?: "Erro ao carregar categorias"
            ))
        }
    }
}
package com.example.catalogapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalogapp.data.ProductRepository
import com.example.catalogapp.model.Product

class CatalogViewModel : ViewModel() {
    val allProducts: LiveData<List<Product>> = ProductRepository.products
    private val _filtered = MutableLiveData<List<Product>>()
    val filtered: LiveData<List<Product>> = _filtered

    init { _filtered.value = allProducts.value }

    fun filter(query: String) {
        _filtered.value = if (query.isBlank()) allProducts.value
        else allProducts.value?.filter {
            it.title.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
        }
    }
}

package com.example.catalogapp.model

data class Product(
    val id: Long, val title: String, val price: Double, val description: String,
    val imageUrl: String, val category: String
)

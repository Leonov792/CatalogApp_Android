package com.example.catalogapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catalogapp.model.Product

object ProductRepository {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        _products.value = listOf(
            Product(1, "Диван «Осло» угловой", 89990.0, "Велюр, 3 места, 280×95 см",
                "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=400", "Мебель"),
            Product(2, "Стол обеденный «Норд»", 45990.0, "Массив дуба, 180×90 см",
                "https://images.unsplash.com/photo-1577140917170-285929fb55b7?w=400", "Мебель"),
            Product(3, "Холодильник Bosch Serie 6", 67990.0, "NoFrost, A++, 389 л",
                "https://images.unsplash.com/photo-1571175443880-49e1d25b2bc5?w=400", "Техника"),
            Product(4, "Кофемашина DeLonghi", 34990.0, "Автоматическая, 15 бар",
                "https://images.unsplash.com/photo-1570087935641-cf871e2bfafd?w=400", "Техника"),
            Product(5, "Люстра «Флоренция»", 18790.0, "Металл/стекло, 6×E14, 120 см",
                "https://images.unsplash.com/photo-1524484485831-a92ffc0de03f?w=400", "Свет"),
            Product(6, "Ковёр «Персеполис»", 45990.0, "Шерсть, 200×290 см, ручная работа",
                "https://images.unsplash.com/photo-1600166898405-da9535204843?w=400", "Декор"),
        )
    }
}

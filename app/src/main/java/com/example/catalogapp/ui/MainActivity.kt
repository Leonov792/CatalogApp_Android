package com.example.catalogapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catalogapp.databinding.ActivityMainBinding
import com.example.catalogapp.databinding.ItemProductBinding
import com.example.catalogapp.model.Product
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CatalogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CatalogViewModel::class.java]
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter { Snackbar.make(binding.root, "Добавлено: ${it.title}", Snackbar.LENGTH_SHORT).show() }
        binding.rvProducts.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?) = q?.let { viewModel.filter(it); true } ?: false
            override fun onQueryTextChange(q: String?) = q?.let { viewModel.filter(it); true } ?: false
        })

        viewModel.filtered.observe(this) { adapter.submitList(it) }
    }

    inner class ProductAdapter(private val onClick: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.VH>() {
        private var items: List<Product> = listOf()
        fun submitList(list: List<Product>) { items = list; notifyDataSetChanged() }
        override fun onCreateViewHolder(p: ViewGroup, t: Int) = VH(ItemProductBinding.inflate(LayoutInflater.from(p.context), p, false))
        override fun onBindViewHolder(h: VH, i: Int) {
            val product = items[i]
            h.b.tvTitle.text = product.title
            h.b.tvPrice.text = "%,.0f ₽".format(product.price)
            h.b.tvDesc.text = product.description
            Glide.with(h.itemView).load(product.imageUrl).into(h.b.ivProduct)
            h.itemView.setOnClickListener { onClick(product) }
        }
        override fun getItemCount() = items.size
        inner class VH(val b: ItemProductBinding) : RecyclerView.ViewHolder(b.root)
    }
}

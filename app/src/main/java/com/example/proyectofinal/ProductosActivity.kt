package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal.databinding.ActivityProductosBinding
import kotlinx.coroutines.launch

class Productos : AppCompatActivity() {
    private lateinit var binding: ActivityProductosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val products = RetrofitFactory.api.getProducts()
            binding.recyclerProductos.adapter = ApiAdapter(products)
        }

        binding.recyclerProductos.layoutManager = LinearLayoutManager(this)
    }
}
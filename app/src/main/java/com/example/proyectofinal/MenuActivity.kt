package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        binding.btnPerfil.setOnClickListener {
            startActivity(Intent(this, Perfil::class.java))
        }
        binding.btnProductos.setOnClickListener {
            startActivity(Intent(this, Productos::class.java))

        }
    }
}

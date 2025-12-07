package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.proyectofinal.databinding.ActivityProfileBinding
import kotlinx.coroutines.launch

class Perfil : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val response = RetrofitFactory.instance.getRandomUser()
            val user = response.results[0]

            binding.txtNombre.text = "Nombre: " + "${user.name.first} ${user.name.last}"
            binding.txtCorreo.text = "Email: " + user.email
            binding.txtTelefono.text = "Telefono: " + user.phone

            binding.txtLocation.text =
                "Locación: " + "${user.location.city}, ${user.location.state}, ${user.location.country}"

            binding.txtBirthday.text =
                "Fecha: ${user.dob.date.take(10)}  (Edad: ${user.dob.age})"

            binding.txtRegistered.text =
                "Registro: ${user.registered.date.take(10)}  (Hace ${user.registered.age} años)"

            binding.txtUUID.text = "UUID: " + user.login.uuid
            binding.txtUsername.text = "Usuario: " + user.login.username

            Glide.with(this@Perfil)
                .load(user.picture.large)
                .into(binding.imgPerfil)
        }
    }
}
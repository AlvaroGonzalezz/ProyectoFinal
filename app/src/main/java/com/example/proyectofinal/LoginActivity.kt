package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Recuperar último correo
        val shared = getSharedPreferences("APP_PREFS", MODE_PRIVATE)
        binding.emailEt.setText(shared.getString("last_email", ""))

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passEt.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {

                // Guardar último correo
                shared.edit().putString("last_email", email).apply()

                startActivity(Intent(this, MenuActivity::class.java))
                finish()

            }.addOnFailureListener {
                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

        binding.goRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}

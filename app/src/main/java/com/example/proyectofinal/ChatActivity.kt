package com.example.proyectofinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal.databinding.ActivityChatBinding

// Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var database: DatabaseReference
    private lateinit var adapter: ChatAdapter

    private val messagesList = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase Realtime Database
        database = FirebaseDatabase.getInstance().getReference("messages")

        // Configurar RecyclerView
        adapter = ChatAdapter(messagesList)
        binding.recyclerMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerMessages.adapter = adapter

        // Escuchar mensajes en tiempo real
        listenForMessages()

        // Botón enviar
        binding.btnSend.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        val text = binding.editMessage.text.toString().trim()
        if (text.isEmpty()) return

        val user = FirebaseAuth.getInstance().currentUser?.email ?: "Anónimo"
        val msg = Message(user, text, System.currentTimeMillis())

        // Enviar mensaje
        val id = database.push().key!!
        database.child(id).setValue(msg)

        binding.editMessage.setText("")
    }

    private fun listenForMessages() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messagesList.clear()

                for (item in snapshot.children) {
                    val msg = item.getValue(Message::class.java)
                    if (msg != null) messagesList.add(msg)
                }

                adapter.notifyDataSetChanged()
                binding.recyclerMessages.scrollToPosition(messagesList.size - 1)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}

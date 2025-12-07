package com.example.proyectofinal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.ItemMessageBinding

class ChatAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = messages[position]
        holder.binding.txtUser.text = msg.user
        holder.binding.txtMessage.text = msg.text
    }

    override fun getItemCount() = messages.size
}

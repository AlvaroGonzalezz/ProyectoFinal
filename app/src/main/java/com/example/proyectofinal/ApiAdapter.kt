package com.example.proyectofinal

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinal.databinding.ItemProductoBinding

class ApiAdapter(private val lista: List<Producto>) : RecyclerView.Adapter<ApiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        holder.binding.txtTitle.text = "Titulo: " + item.title
        holder.binding.txtDescription.text = "Descripción: " + item.description
        holder.binding.txtPrice.text = "$${item.price}"


        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(holder.binding.imgProduct)
    }

    override fun getItemCount(): Int = lista.size

    inner class ViewHolder(val binding: ItemProductoBinding) :
        RecyclerView.ViewHolder(binding.root)
}
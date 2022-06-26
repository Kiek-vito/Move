package com.example.move.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.move.R
import com.example.move.model.Movies


class CustomAdd(private val names: List<Movies>) : RecyclerView
.Adapter<CustomAdd.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val largeTextView: TextView = itemView.findViewById(R.id.title_movie)
        val smallTextView: TextView = itemView.findViewById(R.id.title_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView.text = names[position].title
        holder.smallTextView.text = names[position].description
    }

    override fun getItemCount() = names.size
}
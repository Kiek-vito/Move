package com.example.move.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.move.databinding.ItemMovieBinding
import com.example.move.model.Movies

class MoviesPageAdapter: PagingDataAdapter<Movies, MoviesViewHolder>(MovieComparator) {


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        Log.d("QWE","onBindView ${movie?.src}")
        holder.binding.apply {

            titleMovie.text = movie?.title
            titleDescription.text = movie?.description

            Glide
                .with(holder.itemView.context)
                .load(movie?.src)
                .into(holder.binding.imageView)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }
}
class MoviesViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){

}
object MovieComparator : DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        Log.d("QWE","onBindView")
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem == newItem
    }
}
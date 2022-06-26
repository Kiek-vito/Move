package com.example.move.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.move.databinding.ActivityMainBinding
import com.example.move.ui.adapter.MoviesPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rv.layoutManager = LinearLayoutManager(this)


        //Error observes
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        //Create adapter
        val adapter = MoviesPageAdapter()
        binding.rv.adapter = adapter

        //Progress dialog
        adapter.addLoadStateListener { loadState ->
            binding.progressDialog.isVisible = loadState.refresh is LoadState.Loading ||
                    loadState.append is LoadState.Loading
        }

        //Data observes
        lifecycleScope.launch {
            viewModel.getMovieList().observe(this@MainActivity){
                it?.let {
                    adapter.submitData(lifecycle,it)
                }
            }
        }







    }
}
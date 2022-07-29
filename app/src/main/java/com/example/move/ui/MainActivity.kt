package com.example.move.ui

import android.graphics.LightingColorFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.move.databinding.ActivityMainBinding
import com.example.move.ui.adapter.MoviesPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
        val decorate = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rv.addItemDecoration(decorate)



        //Create adapter
        val adapter = MoviesPageAdapter()
        binding.rv.adapter = adapter




        //Data observes
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
        //Progress dialog
       lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED){
               adapter.loadStateFlow.collect{
                   binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                   binding.appendProgress.isVisible = it.source.append is LoadState.Loading

               }
           }
       }







    }
}
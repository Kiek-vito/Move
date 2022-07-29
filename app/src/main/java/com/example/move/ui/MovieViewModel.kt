package com.example.move.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.move.data.repository.MoviesRepository
import com.example.move.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {

    private var _movies = getMovieList()
    val movies: Flow<PagingData<Movies>>
        get() = _movies




    private fun getMovieList(): Flow<PagingData<Movies>> {
        return repository.getAllMovies().cachedIn(viewModelScope)
    }


}
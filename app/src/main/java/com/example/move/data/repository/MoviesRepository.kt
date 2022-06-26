package com.example.move.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.move.data.api.MovieService
import com.example.move.data.paging.MoviePagingSource
import com.example.move.model.Movies
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: MovieService
){
    fun getAllMovies():LiveData<PagingData<Movies>>{
        Log.d("QWE","repo start")
        val NETWORK_PAGE_SIZE = 1
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 0
            ),
            pagingSourceFactory = {
                MoviePagingSource(api)
            }
            , initialKey = 1
        ).liveData

    }

}

package com.example.move.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.move.data.api.MovieService
import com.example.move.data.dto.toMovies
import com.example.move.model.Movies
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val movieService: MovieService
) : PagingSource<Int, Movies>(){


    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        return try {
            val position = params.key ?: 1
            val response = movieService.getMovies("rmqHqcO6GovGBAZy2AObaG6608d1KPGe",(position-1)*20)
            LoadResult.Page(data = response.results.map { it.toMovies() }, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)


        } catch (e: Exception) {
            LoadResult.Error(e)

        }
    }
}
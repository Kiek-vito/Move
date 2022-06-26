package com.example.move.data.api

import com.example.move.common.Constants
import com.example.move.data.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {
    @GET("all.json")
     suspend fun getMovies(
        @Query("api-key") apiKey: String = Constants.API_KEY,
        @Query("offset") offset : Int=0
    ): MovieResponse

}
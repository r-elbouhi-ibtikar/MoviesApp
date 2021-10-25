package com.rami.moviesapp.data.remote

import com.rami.moviesapp.data.model.Movie
import com.rami.moviesapp.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
interface MovieApi {
    @GET("3/movie/now_playing")
    suspend fun getMovies(
        @Query("page") pageNumber: Int
    ): MovieListResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path(value = "movieId") movieId: Int
    ): Movie
}
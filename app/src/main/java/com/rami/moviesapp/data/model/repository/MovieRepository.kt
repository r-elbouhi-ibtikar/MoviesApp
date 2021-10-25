package com.rami.moviesapp.data.model.repository

import com.rami.moviesapp.data.remote.MovieApi

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
class MovieRepository(
    private val movieApi: MovieApi
) {
    suspend fun getMovies(pageNumber: Int) =
        movieApi.getMovies(pageNumber)

    suspend fun getMovieDetails(movieId: Int) =
        movieApi.getMovieDetails(movieId)
}
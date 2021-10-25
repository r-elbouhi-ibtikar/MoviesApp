package com.rami.moviesapp.ui.movieDetails

import androidx.lifecycle.ViewModel
import com.rami.moviesapp.data.model.repository.MovieRepository

/**
 * Created by Rami El-bouhi on 25,October,2021
 */
class MovieDetailsViewModel(
    val movieRepository: MovieRepository
) : ViewModel() {

    fun getDetails(movieId: Int) {

    }
}
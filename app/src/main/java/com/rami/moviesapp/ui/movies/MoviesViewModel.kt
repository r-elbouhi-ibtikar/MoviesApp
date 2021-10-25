package com.rami.moviesapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rami.moviesapp.data.model.Movie
import com.rami.moviesapp.data.model.repository.MovieRepository
import com.rami.moviesapp.data.model.repository.paged.MovieSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
class MoviesViewModel(
    movieRepository: MovieRepository
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MovieSource(movieRepository)
    }.flow

}
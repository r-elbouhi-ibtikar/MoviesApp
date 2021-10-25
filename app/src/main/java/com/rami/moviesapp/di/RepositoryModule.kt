package com.rami.moviesapp.di

import com.rami.moviesapp.data.model.repository.MovieRepository
import com.rami.moviesapp.data.remote.MovieApi
import org.koin.dsl.module

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
val repositoryModule = module {
    single { createRepository(get()) }
}

fun createRepository(
    movieApi: MovieApi
) : MovieRepository = MovieRepository(movieApi)
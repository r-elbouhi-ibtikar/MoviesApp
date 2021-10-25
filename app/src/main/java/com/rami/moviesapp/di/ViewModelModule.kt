package com.rami.moviesapp.di

import com.rami.moviesapp.ui.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
}
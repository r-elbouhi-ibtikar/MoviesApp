package com.rami.moviesapp.ui.movies

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.rami.moviesapp.data.model.Movie
import com.rami.moviesapp.ui.movies.screen.MoviesScreen
import com.rami.moviesapp.ui.theme.PagingComposeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingComposeTheme {
                MoviesScreen(moviesViewModel) { movie: Movie ->
                    Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
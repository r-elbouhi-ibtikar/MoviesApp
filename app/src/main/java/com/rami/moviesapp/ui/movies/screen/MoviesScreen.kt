package com.rami.moviesapp.ui.movies.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.rami.moviesapp.BuildConfig
import com.rami.moviesapp.R
import com.rami.moviesapp.data.model.Movie
import com.rami.moviesapp.ui.movies.MoviesViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
@Composable
fun MoviesScreen(moviesViewModel: MoviesViewModel, selectedMovie: (Movie) -> (Unit)) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "PopularMovies") }
            )
        },
        bodyContent = {
            MovieList(movies = moviesViewModel.movies, selectedMovie)
        }
    )
}

@Composable
fun MovieList(movies: Flow<PagingData<Movie>>, selectedMovie: (Movie) -> (Unit)) {
    val lazyMovieItems = movies.collectAsLazyPagingItems()

    LazyColumn {

        items(lazyMovieItems) { movie ->
            MovieItem(movie = movie!!, selectedMovie)
        }

        lazyMovieItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, selectedMovie: (Movie) -> (Unit)) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable { selectedMovie(movie) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovieImage(
            BuildConfig.IMAGE_URL + movie.backdrop_path,
            modifier = Modifier
                .padding(start = 16.dp)
                .preferredSize(90.dp)
        )
        MovieTitle(
            movie.title ?: "",
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp)
        )
    }
}

@Composable
fun MovieImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        data = imageUrl,
        modifier = modifier,
        fadeIn = true,
        contentScale = ContentScale.Crop,
        loading = {
            Image(vectorResource(id = R.drawable.ic_photo), alpha = 0.45f)
        },
        error = {
            Image(vectorResource(id = R.drawable.ic_photo), alpha = 0.45f)
        }
    )
}

@Composable
fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}
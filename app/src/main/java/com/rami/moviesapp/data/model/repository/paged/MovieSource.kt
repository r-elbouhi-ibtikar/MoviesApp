package com.rami.moviesapp.data.model.repository.paged

import androidx.paging.PagingSource
import com.rami.moviesapp.data.model.Movie
import com.rami.moviesapp.data.model.repository.MovieRepository

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
class MovieSource(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = movieRepository.getMovies(nextPage)

            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
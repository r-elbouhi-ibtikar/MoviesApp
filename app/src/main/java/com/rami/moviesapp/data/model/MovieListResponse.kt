package com.rami.moviesapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rami El-bouhi on 24,October,2021
 */
data class MovieListResponse(
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("page") val page: Int
)
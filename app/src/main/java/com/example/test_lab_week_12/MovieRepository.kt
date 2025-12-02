package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.test_lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "c764dd3c633c6ce3cf638ad3871936b6"

    fun fetchMovies(): Flow<List<Movie>> =
        flow {
            emit(movieService.getPopularMovies(apiKey).results)
        }.flowOn(Dispatchers.IO)
}

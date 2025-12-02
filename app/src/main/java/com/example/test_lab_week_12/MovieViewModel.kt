package com.example.test_lab_week_12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import android.util.Log

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies = _popularMovies

    private val _error = MutableStateFlow("")
    val error = _error

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            movieRepository.fetchMovies().collect { movies ->

                Log.d("MovieDebug", "Before sort: ${movies.take(5).map { it.popularity }}")

                val sortedMovies = movies.sortedByDescending { it.popularity }

                Log.d("MovieDebug", "After sort:  ${sortedMovies.take(5).map { it.popularity }}")

                _popularMovies.value = sortedMovies
            }
        }
    }

}


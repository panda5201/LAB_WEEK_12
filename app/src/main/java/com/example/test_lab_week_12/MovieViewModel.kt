package com.example.test_lab_week_12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import com.example.test_lab_week_12.model.Movie

class MovieViewModel (private val movieRepository: MovieRepository)
    : ViewModel() {

    init {
        fetchPopularMovies()
    }

    // Mendefinisikan LiveData
    val popularMovies: LiveData<List<Movie>>
        get() = movieRepository.movies
    val error: LiveData<String>
        get() = movieRepository.error

    // Mengambil film dari API
    private fun fetchPopularMovies() {
        // Meluncurkan coroutine di viewModelScope
        // Dispatchers.IO digunakan untuk operasi jaringan
        viewModelScope.launch (Dispatchers.IO) {
            movieRepository.fetchMovies()
        }
    }
}
package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.model.Movie

class MovieRepository (private val movieService: MovieService) {
    // Ganti dengan API Key Anda
    private val apiKey = "c764dd3c633c6ce3cf638ad3871936b6"

    // LiveData yang berisi List of movies
    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    // LiveData yang berisi pesan error
    private val errorLiveData  = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    // Mengambil film dari API
    suspend fun fetchMovies() {
        try {
            // Dapatkan List of popular movies dari API
            val popularMovies = movieService.getPopularMovies (apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
            exception.printStackTrace()
            errorLiveData.postValue("Error: ${exception.message}")
        }

    }
}
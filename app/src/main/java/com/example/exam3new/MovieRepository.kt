package com.example.exam3new

import android.content.Context


class MovieRepository(context: Context) {


    private val movieDB = AppDatabase.getInstance(context)

    val favoriteMovieList: List<Movie>? = movieDB?.getMovieDao()?.getAllFavMovies()

    fun add(movie: Movie) {
        movieDB?.getMovieDao()?.addFavorite(movie)
    }

    fun remove(movie: Movie) {
        movie.title?.let { movie.poster?.let { it1 ->
            movieDB?.getMovieDao()?.removeFavorite(it,
                it1
            )
        } }
    }

}
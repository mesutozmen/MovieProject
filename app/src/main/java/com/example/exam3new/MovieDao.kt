package com.example.exam3new

import androidx.room.*

@Dao
interface MovieDao {


    @Query("SELECT * FROM movie")
    fun getAllFavMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favMovie: Movie)

    @Query("DELETE FROM movie WHERE Title = :title and poster= :imageUrl")
    fun removeFavorite(title: String, imageUrl: String)


   /* @Insert
    suspend fun addMovie(movie: Movie)

    @Query("SELECT*FROM movie  ORDER BY favorite DESC")
     suspend  fun getAllMovie() : List<Movie>

    @Query("DELETE FROM movie")
     suspend fun clearTable()

    @Query("DELETE FROM movie WHERE id = :movieId")
    fun deleteByMovieId(movieId: Int)

    @Query("UPDATE movie SET favorite = :end_address WHERE id = :tid")
    fun updateMovie(tid: Int, end_address: Boolean?): Int


    @Delete
    suspend fun deleteUser(movie: Movie)
*/

}
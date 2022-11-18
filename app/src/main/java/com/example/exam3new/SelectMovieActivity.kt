package com.example.exam3new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.exam3new.databinding.ActivitySelectMovieBinding

class SelectMovieActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectMovieBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySelectMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.extras?.getSerializable("movie") as Movie


        Glide.with(this).load(movie.poster).into(binding.infoPoster)
        binding.infoTitle.text=movie.title
        binding.infoYear.text=movie.year
        binding.infoLike.isChecked = movie.favorite

        binding.infoLike.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                MovieRepository(this).add(movie)
            } else {
                MovieRepository(this).remove(movie)
            }


        }


    }
}
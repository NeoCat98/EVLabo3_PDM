package com.example.moviedex.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.moviedex.Movie
import com.example.moviedex.R
import kotlinx.android.synthetic.main.activity_movie_info.*
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)
        val reciever: Movie = intent.extras.getParcelable("com.example.movie")
        bind(reciever)
    }

    fun bind(movie:Movie){
        actors_main_content.text = movie.Actors
        director_main_content.text = movie.Director
        genre_main_content.text = movie.Genre
        movie_rate_main_content.text = movie.Ratings
        movie_title_main_content.text = movie.Title
        released_main_content.text = movie.Released
        awards_main_content.text = movie.Awards
        rated_main_content.text = movie.Rated
        runtime_main_content.text = movie.Runtime
        writer_main_content.text = movie.Writer
        plot_main_content.text = movie.plot
        Glide.with(this)
            .load(movie.Poster)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image_main_content)
    }
}

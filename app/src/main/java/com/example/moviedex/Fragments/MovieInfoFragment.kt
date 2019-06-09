package com.example.moviedex.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.moviedex.Movie

import com.example.moviedex.R
import kotlinx.android.synthetic.main.activity_movie_info.view.*
import kotlinx.android.synthetic.main.fragment_movie_info.*
import kotlinx.android.synthetic.main.fragment_movie_info.view.*


class MovieInfoFragment : Fragment() {
    private var movie = Movie()

    companion object {
        fun newInstance(movie: Movie): MovieInfoFragment{
            val newFragment = MovieInfoFragment()
            newFragment.movie = movie
            return newFragment
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        bindData(view)
        return view
    }

    fun bindData(view:View){
        view.actors_main_content_fragment.text = movie.Actors
        view.director_main_content_fragment.text = movie.Director
        view.genre_main_content_fragment.text = movie.Genre
        view.movie_rate_main_content_fragment.text = movie.Ratings
        view.movie_title_main_content_fragment.text = movie.Title
        view.runtime_main_content_fragment.text = movie.Runtime
        view.released_main_content_fragment.text = movie.Released
        view.awards_main_content_fragment.text = movie.Awards
        view.rated_main_content_fragment.text = movie.Rated
        view.writer_main_content_fragment.text = movie.Writer
        view.plot_main_content_fragment.text = movie.plot
        Glide.with(this)
            .load(movie.Poster)
            .placeholder(R.drawable.ic_launcher_background)
            .into(view.image_main_content_fragment)
    }
}

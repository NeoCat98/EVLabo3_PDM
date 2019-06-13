package com.example.moviedex.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.res.Configuration
import com.example.moviedex.Adapters.MovieAdapter
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.MoviePreview
import com.example.moviedex.R
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import java.lang.ClassCastException


class MovieFragment : Fragment() {
    private lateinit var movies: List<Movie>
    private lateinit var movieAdapter: MovieAdapter
    var listenerTool :  SearchNewMovieListener? = null

    companion object {
        fun newInstance(dataset : List<Movie>): MovieFragment {
            val newFragment = MovieFragment()
            newFragment.movies = dataset
            return newFragment
        }
    }

    interface SearchNewMovieListener{
        fun managePortraitItemClick(movie: Movie)

        fun manageLandscapeItemClick(movie: Movie)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        if(savedInstanceState != null)
            movies = savedInstanceState.getParcelableArrayList<Movie>("KEY_MOVIE")!!
        initRecyclerView(resources.configuration.orientation, view)

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("KEY_MOVIE", ArrayList(movies))
        super.onSaveInstanceState(outState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchNewMovieListener) {
            listenerTool = context
        } else {
            throw ClassCastException("Se necesita una implementacion de  la interfaz")
        }
    }
    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    fun initRecyclerView(orientation:Int, container:View){

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            movieAdapter = MovieAdapter(
                movies,{ movie: Movie -> listenerTool?.managePortraitItemClick(movie) })

        }else{
            movieAdapter = MovieAdapter(
                movies,{ movie: Movie -> listenerTool?.manageLandscapeItemClick(movie) })
        }
        container.list.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this.context,1)
            adapter = movieAdapter
        }
    }

    fun updateMovieAdapter(movieList: List<Movie>){
        movieAdapter.changeDataSet(movieList)
    }

}

package com.example.moviedex.Activities

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.moviedex.Movie
import com.example.moviedex.Fragments.MovieFragment
import com.example.moviedex.Fragments.MovieInfoFragment
import com.example.moviedex.R
import com.example.moviedex.Fragments.SearchFragment

class MainActivity : AppCompatActivity(), SearchFragment.onSearchListener,
    MovieFragment.SearchNewMovieListener {

    private lateinit var movie:List<Movie>

    private lateinit var movieFragment:MovieFragment
    private lateinit var movieInfoFragment: MovieInfoFragment


    override fun managePortraitItemClick(movie: Movie) {
        val movieBundle = Bundle()
        movieBundle.putParcelable("com.example.movie", movie)
        startActivity(Intent(this, MovieInfoActivity::class.java).putExtras(movieBundle))
    }

    override fun manageLandscapeItemClick(movie: Movie) {
        movieInfoFragment = MovieInfoFragment.newInstance(movie)
        changeFragment(R.id.fl_lan_fragment_content, movieInfoFragment)
    }

    override fun searchMovie(ref: String) {
        if(ref.isEmpty()){
            Toast.makeText(this,"You need to write the name of the movie",Toast.LENGTH_LONG).show()
        }else{
            //Realiza la busqueda

            //movieFragment.updateMovieAdapter(List<Movie>)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie1 = Movie("Star Wars: Episode IV - A New Hope",
            "1977", "PG",
            "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
            "25 May 1977", "121 min",
            "Action, Adventure, Fantasy, Sci-Fi","George Lucas",
            "George Lucas","Mark Hamill, Harrison Ford, Carrie Fisher, Peter Cushing",
            "Won 6 Oscars. Another 50 wins & 28 nominations","https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
            "imdbRating:8.6")

        movie = listOf(movie1)
        initMainFragment()
    }

    fun initMainFragment(){
        movieFragment = MovieFragment.newInstance(movie)
        val resource =  R.id.fl_fragment_list
        changeFragment(resource, movieFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

}

package com.example.moviedex.Activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.MoviePreview
import com.example.moviedex.DataBase.ViewModel.MovieDexViewModel
import com.example.moviedex.Fragments.MovieFragment
import com.example.moviedex.Fragments.MovieInfoFragment
import com.example.moviedex.R
import com.example.moviedex.Fragments.SearchFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchFragment.onSearchListener,
    MovieFragment.SearchNewMovieListener {


    private lateinit var movieFragment:MovieFragment
    private lateinit var movieInfoFragment: MovieInfoFragment
    lateinit var viewModel: MovieDexViewModel

    override fun managePortraitItemClick(movie: Movie) {
        val movieBundle = Bundle()
        movieBundle.putParcelable("com.example.movie",movie)
        startActivity(Intent(this, MovieInfoActivity::class.java).putExtras(movieBundle))

    }

    override fun manageLandscapeItemClick(movie: Movie) {
        movieInfoFragment = MovieInfoFragment.newInstance(movie)
        changeFragment(R.id.fl_lan_fragment_content, movieInfoFragment)

    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun searchMovie(ref: String) {
        if(!isNetworkAvailable()){
            Toast.makeText(this,"You need internet connection to be able to search",Toast.LENGTH_LONG).show()
        }else {
            if (ref.isEmpty()) {
                Toast.makeText(this, "You needgit  to write the name of the movie", Toast.LENGTH_LONG).show()
            } else {
                //Realiza la busqueda
                viewModel.search(ref)
                viewModel.todosMain().observe(this, Observer {
                    if (it != null) {
                        movieFragment = MovieFragment.newInstance(it)
                    } else {
                        Toast.makeText(this, "Results not found", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MovieDexViewModel::class.java)
        initMainFragment()
    }

    fun initMainFragment(){
        viewModel.todosMain().observe(this, Observer {
            if(it!=null){
                movieFragment = MovieFragment.newInstance(it)
                val resource =  R.id.fl_fragment_list
                changeFragment(resource,movieFragment)
            }
        })

    }

    private fun changeFragment(id: Int, frag: Fragment){
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

}

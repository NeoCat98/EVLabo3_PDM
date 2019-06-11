package com.example.moviedex.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
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

    override fun managePortraitItemClick(movie: MoviePreview) {
        val movieBundle = Bundle()
        viewModel.title(movie.Title)
        viewModel.getMains().observe(this, Observer {
            if(it!=null){
                movieBundle.putParcelable("com.example.movie",it)
                startActivity(Intent(this, MovieInfoActivity::class.java).putExtras(movieBundle))
            }
        })
    }

    override fun manageLandscapeItemClick(movie: MoviePreview) {
        viewModel.title(movie.Title)
        viewModel.getMains().observe(this, Observer {
            if(it!=null){
                movieInfoFragment = MovieInfoFragment.newInstance(it)
                changeFragment(R.id.fl_lan_fragment_content, movieInfoFragment)
            }
        })

    }

    override fun searchMovie(ref: String) {
        if(ref.isEmpty()){
            Toast.makeText(this,"You need to write the name of the movie",Toast.LENGTH_LONG).show()
        }else{
            //Realiza la busqueda
            viewModel.search(ref)
            viewModel.getPreviews().observe(this, Observer {
                if(it!=null){
                    GlobalScope.launch {
                        viewModel.insertpreview(it.toList())
                    }
                    movieFragment.updateMovieAdapter(it.toList())
                }else{
                    Toast.makeText(this,"Results not found",Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MovieDexViewModel::class.java)
        initMainFragment()
    }

    fun initMainFragment(){
        viewModel.todospreview().observe(this, Observer {
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

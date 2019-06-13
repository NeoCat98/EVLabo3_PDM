package com.example.moviedex.DataBase.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedex.DataBase.DAO.previewDao
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.MoviePreview
import com.example.moviedex.DataBase.Network.Network
import com.example.moviedex.DataBase.Repository.movieRepo
import com.example.moviedex.DataBase.Roomie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDexViewModel(private val app : Application): AndroidViewModel(app) {

    private val repository: movieRepo
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        val movieoDAO = Roomie.getInstance(app).movieDao()
        val previeDAO = Roomie.getInstance(app).previewDao()
        repository = movieRepo(movieoDAO, previeDAO, Network.getNetworkInstance())
    }
    private val movieslist = MutableLiveData<ArrayList<MoviePreview>>()

    private val movieResult = MutableLiveData<MutableList<Movie>>()


    suspend fun insertpreview(preview: List<MoviePreview>) = repository.insertPreview(preview)

    fun todospreview(): LiveData<List<MoviePreview>> = repository.todosPreview()

    suspend fun nukepreview() = repository.nukePreview()


    suspend fun nukeMovie() = repository.nukeMain()

    fun search(search: String) = viewModelScope.launch  {
        this@MovieDexViewModel.nukeMovie()
        val response = repository.getPreview(search).await()
        if(response.isSuccessful)with(response.body()?.Search){
            this?.forEach {
                getMovies(it.Title)
            }
        }else {
            Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
        }
    }

    fun getMovies(title:String) = viewModelScope.launch {
        val response = repository.getMain(title).await()
        if(response.isSuccessful)with(response.body()){
            this@MovieDexViewModel.insertMain(this!!)
        }
    }


    private suspend fun insertMain(preview: Movie) = repository.insertMain(preview)

    fun todosMain(): LiveData<List<Movie>> = repository.todosMain()


}

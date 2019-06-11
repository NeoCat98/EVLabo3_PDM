package com.example.moviedex.DataBase.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedex.DataBase.DAO.previewDao
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.MoviePreview
import com.example.moviedex.DataBase.Network.Network
import com.example.moviedex.DataBase.Repository.movieRepo
import com.example.moviedex.DataBase.Roomie
import kotlinx.coroutines.launch

class ViewModel(private val app : Application): AndroidViewModel(app) {

    private val repository: movieRepo

    init{
        val movieoDAO = Roomie.getInstance(app).movieDao()
        val previeDAO = Roomie.getInstance(app).previewDao()
        repository = movieRepo(movieoDAO,previeDAO, Network.getNetworkInstance())
    }

    private suspend fun insertpreview(preview: MoviePreview) = repository.insertPreview(preview)

    fun todospreview(): LiveData<List<MoviePreview>> = repository.todosPreview()

    private suspend  fun nukepreview() = repository.nukePreview()


    fun search(search:String) = viewModelScope.launch {
        this@ViewModel.nukepreview()
        val response = repository.getPreview(search).await()

        if(response.isSuccessful) with(response){
            this.body()?.Search.forEach { this@ViewModel.insertpreview(it) }
        }else with(response){
            when(response.code()){
                404 -> {
                    Toast.makeText(app,"Oh shxt", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private suspend fun insertMain(preview: Movie) = repository.insertMain(preview)

    fun todosMain(): LiveData<List<Movie>> = repository.todosMain()

    private suspend  fun nukeMain() = repository.nukeMain()


    fun title(search:String) = viewModelScope.launch {
        this@ViewModel.nukepreview()
        val response = repository.getMain(search).await()

        if(response.isSuccessful) with(response){
            this.body().also{ this@ViewModel.insertMain(it!!) }



        }else with(response){
            when(response.code()){
                404 -> {
                    Toast.makeText(app,"Oh shxt", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
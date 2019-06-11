package com.example.moviedex.DataBase.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviedex.DataBase.DAO.previewDao
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.MoviePreview
import com.example.moviedex.DataBase.Network.Network
import com.example.moviedex.DataBase.Repository.movieRepo
import com.example.moviedex.DataBase.Roomie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(private val app : Application): AndroidViewModel(app) {

    private val repository: movieRepo
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        val movieoDAO = Roomie.getInstance(app).movieDao()
        val previeDAO = Roomie.getInstance(app).previewDao()
        repository = movieRepo(movieoDAO, previeDAO, Network.getNetworkInstance())
    }

    private val movieslist = MutableLiveData<MutableList<MoviePreview>>()

    private val movieResult = MutableLiveData<Movie>()


    private suspend fun insertpreview(preview: MoviePreview) = repository.insertPreview(preview)

    fun todospreview(): LiveData<List<MoviePreview>> = repository.todosPreview()

    private suspend fun nukepreview() = repository.nukePreview()


    fun search(search: String) =
        scope.launch {
            val response = repository.getPreview(search).await()
            if (response.isSuccessful) {
                when (response.code()) {
                    200 -> movieslist.postValue(
                        response.body()?.Search?.toMutableList() ?: arrayListOf(
                            MoviePreview(
                                Title = "Dummy 1"
                            ), MoviePreview(Title = "Dummy 2")
                        )
                    )
                }
            } else {
                Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
            }
        }


    private suspend fun insertMain(preview: Movie) = repository.insertMain(preview)

    fun todosMain(): LiveData<List<Movie>> = repository.todosMain()

    private suspend fun nukeMain() = repository.nukeMain()


    fun title(name: String) {
        scope.launch {
            val response = repository.getMain(name).await()
            if (response.isSuccessful) with(response) {
                when (this.code()) {
                    200 -> movieResult.postValue(this.body())
                }
            } else {
                Toast.makeText(app, "Ocurrio un error", Toast.LENGTH_LONG).show()
            }
        }


    }


    fun getPreviews(): LiveData<MutableList<MoviePreview>> = movieslist

    fun getMains(): LiveData<Movie> = movieResult

}

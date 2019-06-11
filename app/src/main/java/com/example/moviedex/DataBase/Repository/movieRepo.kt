package com.example.moviedex.DataBase.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import com.example.moviedex.DataBase.DAO.movieDAO
import com.example.moviedex.DataBase.DAO.previewDao
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.MoviePreview
import com.example.moviedex.DataBase.Entities.Search
import com.example.moviedex.DataBase.Network.Network

class movieRepo (val movieDAO: movieDAO,val previewDao: previewDao,val network:Network){

    @WorkerThread
    suspend  fun insertPreview(preview:List<MoviePreview>){
        previewDao.insert(preview)
    }

    fun todosPreview(): LiveData<List<MoviePreview>> = previewDao.getall()

    @WorkerThread
    suspend fun nukePreview() = previewDao.nuke()

    fun getPreview(search:String): Deferred<Response<Search>> = network.search(search,"6fd8f447")


    @WorkerThread
    suspend  fun insertMain(movie:Movie){
        movieDAO.insert(movie)
    }

    fun todosMain(): LiveData<List<Movie>> = movieDAO.getall()

    @WorkerThread
    suspend fun nukeMain() = movieDAO.nuke()

    fun getMain(search:String): Deferred<Response<Movie>> = network.getmovie(search,"6fd8f447")



}
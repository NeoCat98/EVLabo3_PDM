package com.example.moviedex.DataBase.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedex.DataBase.Entities.Movie

@Dao
interface movieDAO{
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insert(preview:Movie)

    @Query("SELECT * FROM movies")
    fun getall(): LiveData<List<Movie>>

    @Query("DELETE FROM movies")
    suspend  fun nuke()

}
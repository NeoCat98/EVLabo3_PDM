package com.example.moviedex.DataBase.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedex.DataBase.Entities.MoviePreview

@Dao
interface previewDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(preview:MoviePreview)

    @Query("SELECT * FROM previews")
    fun getall():LiveData<List<MoviePreview>>

    @Query("DELETE FROM previews")
    suspend  fun nuke()

}
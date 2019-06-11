package com.example.moviedex.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedex.DataBase.DAO.movieDAO
import com.example.moviedex.DataBase.DAO.previewDao
import com.example.moviedex.DataBase.Entities.Movie
import com.example.moviedex.DataBase.Entities.MoviePreview


@Database(entities = [Movie::class,MoviePreview::class],version = 1,exportSchema = false)
public abstract class Roomie: RoomDatabase(){
    abstract  fun previewDao():previewDao
    abstract fun movieDao():movieDAO

    companion object {
        @Volatile
        private var INSTANCE : Roomie? = null

        fun getInstance(context: Context):Roomie{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, Roomie::class.java, "MovieDex")
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}


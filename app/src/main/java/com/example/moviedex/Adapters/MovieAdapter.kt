package com.example.moviedex.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.moviedex.Movie
import com.example.moviedex.R


import kotlinx.android.synthetic.main.fragment_movie.view.*


class MovieAdapter(
    var movies:List<Movie>,
    val clickListener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.fragment_movie, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bind(movies[p1], clickListener)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Movie, clickListener: (Movie) -> Unit) = with(itemView){
            tv_genre_fragment.text = item.Genre
            tv_name_fragment.text = item.Title
            tv_rating_fragment.text = item.Rated
            this.setOnClickListener { clickListener(item) }
        }
    }

    fun changeDataSet(newDataSet: List<Movie>) {
        this.movies = newDataSet
        notifyDataSetChanged()
    }

}

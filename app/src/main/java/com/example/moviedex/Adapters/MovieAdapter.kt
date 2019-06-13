package com.example.moviedex.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedex.DataBase.Entities.MoviePreview
import com.example.moviedex.R
import kotlinx.android.synthetic.main.fragment_movie_2.view.*


class MovieAdapter(
    var movies:List<MoviePreview>,
    val clickListener: (MoviePreview) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.fragment_movie_2, p0, false)
        //val view = LayoutInflater.from(p0.context).inflate(R.layout.fragment_movie, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) = p0.bind(movies[p1], clickListener)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: MoviePreview, clickListener: (MoviePreview) -> Unit) = with(itemView){
            //tv_genre_fragment.text = item.Type
            //tv_name_fragment.text = item.Title
            //tv_rating_fragment.text = item.Year
            movieType.text = item.Type
            movieName.text = item.Title
            movieDate.text = item.Year
            Glide.with(this)
                .load(item.Poster)
                .placeholder(R.drawable.ic_launcher_background)
                .into(movieImage)
            this.setOnClickListener { clickListener(item) }
        }
    }

    fun changeDataSet(newDataSet: List<MoviePreview>) {
        this.movies = newDataSet
        notifyDataSetChanged()
    }

}

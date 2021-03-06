package com.movienight.feature.home

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.movienight.R
import com.movienight.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val homeViewModel: HomeViewModel) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.setMovie(movie)
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun setMovie(movie: Movie) {
            itemView.setOnClickListener {
                homeViewModel.onMovieClickedLiveData.postValue(movie)
            }
            itemView.movie_item_textView_movieTitle.setText(movie.title)
            itemView.movie_item_textView_movieOverview.setText(movie.overview)
            if (movie.poster_path != null) {
                val uri = Uri.Builder().scheme("http")
                        .authority("image.tmdb.org")
                        .appendPath("t")
                        .appendPath("p")
                        .appendPath("w185")
                        .appendPath(movie.poster_path.removePrefix("/"))
                        .build()
                Picasso.with(itemView.context).load(uri).into(itemView.movie_item_imageView)
            }
        }
    }
}
package com.example.demoproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproject.R
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.databinding.MovieListBinding

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    var listMovie  = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:MovieListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.movie_list,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listMovie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie.get(position))
    }

    fun setMovieList(listMovies: ArrayList<Movie>){
        listMovie = listMovies
    }


}



class  MovieViewHolder(val binding:MovieListBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(movieItem:Movie){
        binding.tvTitle.text = movieItem.title
        binding.tvDesc.text = movieItem.overview
        val posterUrl = "https://image.tmdb.org/t/p/w500"+movieItem.poster_path
        Glide.with(binding.imgMovie.context).load(posterUrl).into(binding.imgMovie)

    }


}
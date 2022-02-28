package com.example.demoproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproject.R
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.databinding.ArtistListBinding
import com.example.demoproject.databinding.MovieListBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistViewHolder>() {
    var listArtist  = ArrayList<People>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:ArtistListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.artist_list,parent,false)
        return ArtistViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listArtist.size
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(listArtist.get(position))
    }

    fun setArtistList(listMovies: ArrayList<People>){
        listArtist = listMovies
    }


}



class  ArtistViewHolder(val binding:ArtistListBinding ) : RecyclerView.ViewHolder(binding.root){

    fun bind(artist:People){
        binding.artistTitle.text = artist.name
        binding.artistDesc.text = artist.known_for_department
        val posterUrl = "https://image.tmdb.org/t/p/w500"+artist.profile_path
        Glide.with(binding.imgArtist.context).load(posterUrl).into(binding.imgArtist)

    }


}
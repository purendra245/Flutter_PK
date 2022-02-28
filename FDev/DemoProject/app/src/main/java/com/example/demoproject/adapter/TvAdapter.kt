package com.example.demoproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproject.R
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.databinding.MovieListBinding
import com.example.demoproject.databinding.TvListBinding

class TvAdapter : RecyclerView.Adapter<TvViewHolder>() {
    var listTv  = ArrayList<TvResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:TvListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.tv_list,parent,false)
        return TvViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listTv.size
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(listTv.get(position))
    }

    fun setTvList(listMovies: ArrayList<TvResult>){
        listTv = listMovies
    }


}



class  TvViewHolder(val binding:TvListBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(tvItem:TvResult){
        binding.tvTitle.text = tvItem.name
        binding.tvDesc.text = tvItem.overview
        val posterUrl = "https://image.tmdb.org/t/p/w500"+tvItem.poster_path
        Glide.with(binding.imgTv.context).load(posterUrl).into(binding.imgTv)

    }


}
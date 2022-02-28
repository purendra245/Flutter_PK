package com.example.demoproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject.Injector
import com.example.demoproject.R
import com.example.demoproject.adapter.MovieAdapter
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.databinding.ActivityMainBinding
import com.example.demoproject.databinding.ActivityMovieBinding
import com.example.demoproject.viewModel.movie.MovieViewModel
import com.example.demoproject.viewModel.movie.MovieViewModelFactory
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    @Inject
     lateinit var movieFactory:MovieViewModelFactory
    private lateinit var binding:ActivityMovieBinding
    private lateinit var movieViewModel:MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie)

        (application as Injector)
            .createMovieSubComponent()
            .inject(this)

        movieViewModel =  ViewModelProvider(this,movieFactory)
            .get(MovieViewModel::class.java)

        initRecylerView()
        setRecylerViewData()


    }

    fun initRecylerView(){
        adapter = MovieAdapter()
        binding.movieRecylerView.layoutManager = LinearLayoutManager(this)
        binding.movieRecylerView.adapter = adapter
    }

    fun setRecylerViewData(){

        binding.progressBar.visibility = View.VISIBLE

        val responseLiveData = movieViewModel.getMovie().observe(this, Observer {
            if(it!=null){
                adapter.setMovieList(it as ArrayList<Movie>)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.update_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_update->{
                updateMenu()
                true
            }

            else ->  super.onOptionsItemSelected(item)
        }

    }

    fun updateMenu(){
        binding.progressBar.visibility = View.VISIBLE
        movieViewModel.updateMovie().observe(this, Observer {
            if(it!=null){
                adapter.setMovieList(it as ArrayList<Movie>)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
            }

        })
    }

}
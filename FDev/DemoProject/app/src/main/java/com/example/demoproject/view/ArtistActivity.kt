package com.example.demoproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.demoproject.adapter.ArtistAdapter
import com.example.demoproject.adapter.MovieAdapter
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.databinding.ActivityArtistBinding
import com.example.demoproject.databinding.ActivityMovieBinding
import com.example.demoproject.viewModel.artist.ArtistViewModel
import com.example.demoproject.viewModel.artist.ArtistViewModelFactory
import com.example.demoproject.viewModel.movie.MovieViewModel
import com.example.demoproject.viewModel.movie.MovieViewModelFactory
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {

    @Inject
    lateinit var artistFactory: ArtistViewModelFactory
    private lateinit var binding: ActivityArtistBinding
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_artist)

        (application as Injector)
            .createArtistSubComponent()
            .inject(this)

        artistViewModel =  ViewModelProvider(this,artistFactory)
            .get(ArtistViewModel::class.java)

        initRecylerView()
        setRecylerViewData()
    }


    fun initRecylerView(){
        adapter = ArtistAdapter()
        binding.artistRecylerView.layoutManager = LinearLayoutManager(this)
        binding.artistRecylerView.adapter = adapter
    }

    fun setRecylerViewData(){

        binding.progressBar.visibility = View.VISIBLE

       artistViewModel.getArtist().observe(this, Observer {
            if(it!=null){
                adapter.setArtistList(it as ArrayList<People>)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
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
        artistViewModel.updateMovie().observe(this, Observer {
            if(it!=null){
                adapter.setArtistList(it as ArrayList<People>)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
            }

        })
    }
}
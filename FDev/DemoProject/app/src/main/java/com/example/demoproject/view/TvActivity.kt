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
import com.example.demoproject.adapter.TvAdapter
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.databinding.ActivityArtistBinding
import com.example.demoproject.databinding.ActivityTvBinding
import com.example.demoproject.viewModel.artist.ArtistViewModel
import com.example.demoproject.viewModel.artist.ArtistViewModelFactory
import com.example.demoproject.viewModel.tv.TVViewModel
import com.example.demoproject.viewModel.tv.TvViewModelFactory
import javax.inject.Inject

class TvActivity : AppCompatActivity() {

    @Inject
    lateinit var tvFactory: TvViewModelFactory
    private lateinit var binding: ActivityTvBinding
    private lateinit var tvViewModel: TVViewModel
    private lateinit var adapter: TvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_tv)

        (application as Injector)
            .createTvSubComponent()
            .inject(this)

        tvViewModel =  ViewModelProvider(this,tvFactory)
            .get(TVViewModel::class.java)

        initRecylerView()
        setRecylerViewData()
    }

    fun initRecylerView(){
        adapter = TvAdapter()
        binding.tvRecylerView.layoutManager = LinearLayoutManager(this)
        binding.tvRecylerView.adapter = adapter
    }

    fun setRecylerViewData(){

        binding.progressBar.visibility = View.VISIBLE

        tvViewModel.getTvShow().observe(this, Observer {
            if(it!=null){
                adapter.setTvList(it as ArrayList<TvResult>)
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
        tvViewModel.updateTvShow().observe(this, Observer {
            if(it!=null){
                adapter.setTvList(it as ArrayList<TvResult>)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
            }

        })
    }
}
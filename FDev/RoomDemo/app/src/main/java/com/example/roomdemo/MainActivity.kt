package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Repository
import com.example.roomdemo.db.SubscribeDatabase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var repository: Repository
    private lateinit var dao: SubscriberDao
    private lateinit var viewModel: SubscriberViewModel
    private lateinit var factory: SubscriberViewModelFactory
    private lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        dao = SubscribeDatabase.getInstance(this).subscribeDao
        repository = Repository(dao)
        factory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        val layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(this) {
                selectedItem:Subscriber->onItemClick(selectedItem)
        }
        binding.listTask.layoutManager = layoutManager
        binding.listTask.adapter = adapter
        dispayData()
        displayMessage()
    }

    fun dispayData(){
        viewModel.getAllData().observe(this, Observer {
            userData->
            adapter.setList(userData)

        })
    }

    fun displayMessage(){
        viewModel.liveDataEvent.observe(this, Observer { its ->
            its.getContentIfNotHandled().let {
                Toast.makeText(applicationContext,it,Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun onItemClick(subscriber: Subscriber){
        Log.i("TAG",subscriber.email)
        viewModel.updateOrDelete(subscriber)

    }
}
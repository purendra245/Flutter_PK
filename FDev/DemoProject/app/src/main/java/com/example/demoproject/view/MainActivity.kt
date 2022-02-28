package com.example.demoproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.demoproject.R
import com.example.demoproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        mainDataBinding.btnMovie.setOnClickListener{
            startActivity(Intent(this,MovieActivity::class.java))
        }

        mainDataBinding.btnArtist.setOnClickListener{
            startActivity(Intent(this,ArtistActivity::class.java))
        }

        mainDataBinding.btnTv.setOnClickListener{
            startActivity(Intent(this,TvActivity::class.java))
        }
    }
}
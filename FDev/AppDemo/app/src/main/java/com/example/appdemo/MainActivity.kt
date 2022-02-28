package com.example.appdemo;

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding_utils: ActivityMainBinding
    private lateinit var view_model : AnViewModel

    private lateinit var view_model_factory: AnViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_utils = DataBindingUtil.setContentView(this,R.layout.activity_main)
        view_model_factory  = AnViewModelFactory(12)
        view_model = ViewModelProvider(this,view_model_factory).get(AnViewModel::class.java)
        binding_utils.myDataViewModel = view_model
        binding_utils.lifecycleOwner = this
20
//        binding_utils.submitButton.setOnClickListener {
//            view_model.updateCount()
////           binding_utils.student = getStudent();
//        }

//        view_model.total_live_data.observe(this, Observer {
//            binding_utils.student = Student(1,  "Alexa ${it}")
//        })







    }


//    private fun getStudent(): Student{
//        return Student(1,  "Alexa ${view_model.getCount()}")
//    }


}

package com.example.roomdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.databinding.ListItemBinding

class MyAdapter(
    val context: Context,private val selectedItem: (Subscriber) -> Unit) : RecyclerView.Adapter<MyviewHolder>(){

     var listSub = ArrayList<Subscriber>()
    lateinit var binding:ListItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
            val layoutInflater = LayoutInflater.from(context)
             binding =  DataBindingUtil.inflate(layoutInflater,R.layout.list_item, parent, false)
            return MyviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSub.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.bindData(binding,listSub.get(position),selectedItem)
    }

    fun setList(list: List<Subscriber>){
        listSub.clear()
        listSub.addAll(list)
        notifyDataSetChanged()
    }


}

class MyviewHolder(binding:ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(binding: ListItemBinding, subscriber: Subscriber,selectedItem: (Subscriber) -> Unit){
        binding.tvUserEmail.text = subscriber.email
        binding.tvUserName.text = subscriber.name
        binding.root.setOnClickListener{
            selectedItem(subscriber)
        }

    }
}
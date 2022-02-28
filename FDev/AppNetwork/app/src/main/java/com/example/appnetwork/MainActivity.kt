package com.example.appnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.model.AlbumModel
import com.example.model.AlbumModelItem
import com.example.network.RetrofitInstance
import com.example.services.AlbumService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val albumSer: AlbumService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
//        val albumResp: LiveData<Response<AlbumModel>> = liveData<Response<AlbumModel>> {
//            emit( albumSer.getAlbum())
//        }

//        val albumResp: LiveData<Response<AlbumModel>> = liveData<Response<AlbumModel>> {
//
//            emit( albumSer.getAlbumDetail(1))
//        }

//        val albumResp: LiveData<Response<AlbumModel>> = liveData<Response<AlbumModel>> {
//
//            emit( albumSer.getAlbumDetail(1))
//        }

//        val albumResp: LiveData<Response<AlbumModelItem>> = liveData<Response<AlbumModelItem>> {
//                val response = albumSer.getAlbumDetailById(1);
//            emit(response )
//        }

//        albumResp.observe(this, Observer {
//                val listIte: MutableListIterator<AlbumModelItem>? = it.body()?.listIterator()
//            if(listIte!=null)
//            while (listIte.hasNext()){
//                val item:AlbumModelItem  = listIte.next()
//                val title  = item.title
//                val id  = item.id
//                val userId  = item.userId
//                val result:String  =  "Album User Id: ${userId}\n"+ "Album Id: ${id}\n"+ "Album Title: ${title}\n\n"
//                tv_album.append(result)
////                Log.v("TAG",id.toString())
//
//            }
//
//
//        })

//        albumResp.observe(this, Observer {
//            val listIte: AlbumModelItem ? = it.body()
//            if(listIte!=null) {
//                val title = listIte.title
//                val id = listIte.id
//                val userId = listIte.userId
//                val result: String =
//                    "Album User Id: ${userId}\n" + "Album Id: ${id}\n" + "Album Title: ${title}\n\n"
//                tv_album.append(result)
////                Log.v("TAG",id.toString())
//            }
//
//
//
//        })


        val postRepo: LiveData<Response<AlbumModelItem>> = liveData<Response<AlbumModelItem>> {
            val response = albumSer.getAlbumPost(AlbumModelItem(1,"myTitle",3))
            emit(response )
        }

        postRepo.observe(this, Observer {
            val listIte: AlbumModelItem ? = it.body()
            if(listIte!=null) {
                val title = listIte.title
                val id = listIte.id
                val userId = listIte.userId
                val result: String =
                    "Album User Id: ${userId}\n" + "Album Id: ${id}\n" + "Album Title: ${title}\n\n"
                tv_album.append(result)
//                Log.v("TAG",id.toString())
            }
        })


    }
}
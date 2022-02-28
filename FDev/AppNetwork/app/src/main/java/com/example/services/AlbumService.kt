package com.example.services

import com.example.model.AlbumModel
import com.example.model.AlbumModelItem
import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbum(): Response<AlbumModel>

    @GET("/albums")
    suspend fun getAlbumDetail(@Query("userId") userId:Int): Response<AlbumModel>

    @GET("/albums/{id}")
    suspend fun getAlbumDetailById(@Path(value = "id") albumId:Int): Response<AlbumModelItem>

    @POST("/albums")
    suspend fun getAlbumPost(@Body albuItem:AlbumModelItem): Response<AlbumModelItem>



}
package com.example.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object{
        val BASE_URL="https://jsonplaceholder.typicode.com"

        fun getRetrofitInstance(): Retrofit {

            val interceptor =  HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }


            val client = OkHttpClient().newBuilder().apply {
                this.addInterceptor(interceptor)
                this.connectTimeout(30,TimeUnit.SECONDS)
                this.readTimeout(30,TimeUnit.SECONDS)
                this.writeTimeout(30,TimeUnit.SECONDS)
            }.build();

            return Retrofit.Builder().
            baseUrl(BASE_URL).
                client(client).
                addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).
            build()
        }
    }
}
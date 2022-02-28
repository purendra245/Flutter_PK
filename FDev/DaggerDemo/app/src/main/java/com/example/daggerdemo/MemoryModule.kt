package com.example.daggerdemo

import android.util.Log
import dagger.Module
import dagger.Provides

@Module
class MemoryModule( val memorySize:Int) {

    @Provides
    fun memoryProvider():MemoryCard{
        Log.i("MYTAG","Memory Size is $memorySize")
        return MemoryCard()
    }

}
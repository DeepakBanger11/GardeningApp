package com.getstarted.flower.room

import android.content.Context
import androidx.room.Room
import com.getstarted.flower.api.PlantApiService
import com.getstarted.flower.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val gson = GsonBuilder().setLenient().create()
    @Provides
    @Singleton
    fun networkRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://perenual.com/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun plantAPI(retrofit: Retrofit): PlantApiService {
        val plantAPI = retrofit.create(PlantApiService::class.java)
        return plantAPI
    }


}

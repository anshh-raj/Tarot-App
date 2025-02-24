package com.example.tarot.di

import com.example.tarot.network.TarotAPI
import com.example.tarot.repository.TarotRepository
import com.example.tarot.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesTarotRepository(api: TarotAPI) = TarotRepository(api)

    @Singleton
    @Provides
    fun providesTarotAPI(): TarotAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TarotAPI::class.java)
    }
}
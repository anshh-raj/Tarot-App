package com.example.tarot.di

import android.content.Context
import androidx.room.Room
import com.example.tarot.data.TarotDatabase
import com.example.tarot.data.TarotDatabaseDAO
import com.example.tarot.network.TarotAPI
import com.example.tarot.repository.TarotDbRepository
import com.example.tarot.repository.TarotRepository
import com.example.tarot.utils.Constants
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
object AppModule {

    @Singleton
    @Provides
    fun providesTarotDbRepository(tarotDatabaseDAO: TarotDatabaseDAO)
    = TarotDbRepository(tarotDatabaseDAO)

    @Singleton
    @Provides
    fun provideTarotDAO(tarotDatabase: TarotDatabase): TarotDatabaseDAO
    = tarotDatabase.tarotDao()

    @Singleton
    @Provides
    fun provideTarotDatabase(@ApplicationContext context: Context): TarotDatabase
    = Room.databaseBuilder(
        context,
        TarotDatabase::class.java,
        name = "tarot_db")
        .fallbackToDestructiveMigrationFrom()
        .build()

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
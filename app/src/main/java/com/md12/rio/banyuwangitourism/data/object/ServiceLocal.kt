package com.md12.rio.banyuwangitourism.data.`object`

import android.app.Application
import androidx.room.Room
import com.md12.rio.banyuwangitourism.data.local.TourismDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceLocal {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, TourismDatabase::class.java, "banyuwangi_tourism.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: TourismDatabase) = database.tourismDao()
}
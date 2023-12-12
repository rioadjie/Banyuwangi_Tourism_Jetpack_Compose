package com.md12.rio.banyuwangitourism.data.`object`


import com.md12.rio.banyuwangitourism.data.local.Dao
import com.md12.rio.banyuwangitourism.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ServiceRepository {
    @Provides
    @ViewModelScoped
    fun provideRepository(tourismDao: Dao) = Repository(tourismDao)
}
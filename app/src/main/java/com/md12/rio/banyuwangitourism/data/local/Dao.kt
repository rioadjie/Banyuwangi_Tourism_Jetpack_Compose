package com.md12.rio.banyuwangitourism.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("UPDATE tourism SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTourism(kulinerList: List<TourismEntity>)

    @Query("SELECT * FROM tourism WHERE isFavorite = 1")
    fun getAllFavoriteTourism(): Flow<List<TourismEntity>>

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flow<List<TourismEntity>>

    @Query("SELECT * FROM tourism WHERE name LIKE '%' || :query || '%'")
    fun searchTourism(query: String): Flow<List<TourismEntity>>


    @Query("SELECT * FROM tourism WHERE id = :id")
    fun getTourism(id: Int): Flow<TourismEntity>
}
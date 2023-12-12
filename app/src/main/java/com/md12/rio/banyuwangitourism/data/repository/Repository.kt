package com.md12.rio.banyuwangitourism.data.repository


import com.md12.rio.banyuwangitourism.data.local.Dao
import com.md12.rio.banyuwangitourism.data.local.TourismEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val tourismDao: Dao) {
    fun getAllTourism() = tourismDao.getAllTourism()
    fun getAllFavoriteTourism() = tourismDao.getAllFavoriteTourism()
    fun getTourism(id: Int) = tourismDao.getTourism(id)
    fun searchTourism(query: String) = tourismDao.searchTourism(query)
    suspend fun insertAllTourism(tourism: List<TourismEntity>) = tourismDao.insertAllTourism(tourism)
    suspend fun updateFavorite(id: Int, isFavorite: Boolean) = tourismDao.updateFavorite(id, isFavorite)
}

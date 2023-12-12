package com.md12.rio.banyuwangitourism.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TourismEntity::class], version = 1, exportSchema = false)
abstract class TourismDatabase : RoomDatabase() {
    abstract fun tourismDao(): Dao
}
package com.example.tarot.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tarot.model.TarotCards

@Database(entities = [TarotCards::class], version = 1, exportSchema = false)
abstract class TarotDatabase: RoomDatabase() {
    abstract fun tarotDao(): TarotDatabaseDAO
}
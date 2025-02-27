package com.example.tarot.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tarot.model.TarotCards
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TarotDatabaseDAO {
    @Query("SELECT * FROM tarot_table")
    fun getTarotCards(): Flow<List<TarotCards>>

    @Query("SELECT * FROM tarot_table WHERE id=:tarotId")
    suspend fun getNoteById(tarotId: UUID):TarotCards

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: TarotCards)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(card: TarotCards)

    @Query("DELETE FROM tarot_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(card: TarotCards)
}
package com.example.tarot.repository

import com.example.tarot.data.TarotDatabaseDAO
import com.example.tarot.model.TarotCards
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TarotDbRepository @Inject constructor(private val tarotDatabaseDAO: TarotDatabaseDAO) {
    suspend fun addTarotCard(card: TarotCards) = tarotDatabaseDAO.insert(card)
    suspend fun updateTarotCard(card: TarotCards) = tarotDatabaseDAO.update(card)
    suspend fun deleteTarotCard(card: TarotCards) = tarotDatabaseDAO.deleteNote(card)
    suspend fun deleteAllTarotCard() = tarotDatabaseDAO.deleteAll()
    fun getAllTarotCard(): Flow<List<TarotCards>> = tarotDatabaseDAO.getTarotCards().flowOn(Dispatchers.IO).conflate()
    suspend fun getTarotCardById(card: TarotCards) = tarotDatabaseDAO.getNoteById(card.id)
}
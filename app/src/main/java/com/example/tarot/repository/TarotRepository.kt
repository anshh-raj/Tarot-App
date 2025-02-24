package com.example.tarot.repository

import android.util.Log
import com.example.tarot.data.DataOrException
import com.example.tarot.model.TCardsItem
import com.example.tarot.network.TarotAPI
import javax.inject.Inject

class TarotRepository @Inject constructor(private val api: TarotAPI) {
    private val dataOrException = DataOrException<ArrayList<TCardsItem>, Boolean, Exception>()

    suspend fun getAllCards(): DataOrException<ArrayList<TCardsItem>, Boolean, Exception>{
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllCards()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }catch (e: Exception){
            dataOrException.e = e
        }
        return dataOrException
    }
}
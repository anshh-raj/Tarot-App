package com.example.tarot.screens.cardScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarot.data.DataOrException
import com.example.tarot.model.TCardsItem
import com.example.tarot.model.TarotCards
import com.example.tarot.repository.TarotDbRepository
import com.example.tarot.repository.TarotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val repository: TarotRepository, private val repositoryDb: TarotDbRepository): ViewModel() {
    val data: MutableState<DataOrException<ArrayList<TCardsItem>, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    init {
        getAllCards()

        viewModelScope.launch(Dispatchers.IO) {
            repositoryDb.getAllTarotCard().distinctUntilChanged().collect{listOfCard ->
                _cardList.value = listOfCard

            }
        }
    }

    private fun getAllCards() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllCards()
            if(data.value.data.toString().isNotEmpty()) data.value.loading = false
        }
    }

    private val _cardList = MutableStateFlow<List<TarotCards>>(emptyList())
    val cardList = _cardList.asStateFlow()

    fun addCard(tarotCards: TarotCards) = viewModelScope.launch {
        repositoryDb.addTarotCard(tarotCards)
    }

    fun removeCard(tarotCards: TarotCards) = viewModelScope.launch {
        repositoryDb.deleteTarotCard(tarotCards)
    }

}
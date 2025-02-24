package com.example.tarot.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarot.data.DataOrException
import com.example.tarot.model.TCardsItem
import com.example.tarot.repository.TarotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: TarotRepository): ViewModel() {
    val data: MutableState<DataOrException<ArrayList<TCardsItem>, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    init {
        getAllCards()
    }

    private fun getAllCards() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllCards()
            if(data.value.data.toString().isNotEmpty()) data.value.loading = false
        }
    }
}
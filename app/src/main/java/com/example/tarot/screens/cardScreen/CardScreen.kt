package com.example.tarot.screens.cardScreen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.tarot.model.TCardsItem
import com.example.tarot.utils.Constants

@Composable
fun CardScreen(navController: NavHostController, cardViewModel: CardViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val listOfCards = cardViewModel.data.value.data
        val readOnlyList: List<TCardsItem> = listOfCards?.toList()?.shuffled() ?: emptyList()
        Log.d("dataa", "CardScreen: ${readOnlyList.toString()}")
        if(cardViewModel.data.value.loading == true){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(readOnlyList.chunked(3)){ cardPair->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        cardPair.forEach { card->
                            Box(
                                modifier = Modifier.weight(1f)
                            ){
                                CardRow(card)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardRow(card: TCardsItem) {
    Card(
        onClick = {

        },
        modifier = Modifier
            .padding(5.dp)
            .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp)),
    ) {
        AsyncImage(
            model = Constants.BASE_URL + card.image,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
        )
    }
}

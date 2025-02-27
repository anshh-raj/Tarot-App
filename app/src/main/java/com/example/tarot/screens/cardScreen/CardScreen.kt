package com.example.tarot.screens.cardScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.tarot.model.TCardsItem
import com.example.tarot.navigation.TarotScreens
import com.example.tarot.utils.Constants

@Composable
fun CardScreen(navController: NavHostController, cardViewModel: CardViewModel = hiltViewModel()) {
    val count = rememberSaveable {
        mutableIntStateOf(0)
    }
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
                                CardRow(card){
                                    count.value +=it
                                    Log.d("count", "CardRow: ${count.intValue}")
                                    if(count.intValue == 3){
                                        navController.navigate(TarotScreens.RevealScreen.name)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardRow(card: TCardsItem, onCLickEvent: (Int) -> Unit) {
    val selected = rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        onClick = {
            selected.value = !selected.value
            if(selected.value){
                onCLickEvent(1)
                // add to database
            } else {
                onCLickEvent(-1)
                // delete from database
            }

        },
        modifier = Modifier
            .padding(5.dp)
            .size(200.dp)
            .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp)),
    ) {
        AsyncImage(
            model = Constants.BASE_URL + card.image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit,
        )
    }
    if(selected.value){
        Box(
            modifier = Modifier
                .padding(5.dp)
                .size(200.dp)
                .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp))
                .background(Color.LightGray.copy(alpha = 0.4f)))
    }

}

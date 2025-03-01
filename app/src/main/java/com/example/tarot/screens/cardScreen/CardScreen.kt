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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.tarot.model.TarotCards
import com.example.tarot.navigation.TarotScreens
import com.example.tarot.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(
    navController: NavHostController,
    cardViewModel: CardViewModel = hiltViewModel(),
    title: String?
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "TarotVerse",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Cyan
                )
            )
        }
    ){innerPadding ->
        val count = rememberSaveable {
            mutableIntStateOf(0)
        }
        val listCards = mutableListOf<TCardsItem>()
        Log.d("titles", "CardScreen: $title")
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val listOfCards = cardViewModel.data.value.data
            val readOnlyList: List<TCardsItem> = listOfCards?.toList()?.shuffled() ?: emptyList()
            Log.d("dataa", "CardScreen: $readOnlyList")
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Please select any 3 cards",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.labelLarge,
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
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
                                        CardRow(card){ i: Int, tCardsItem: TCardsItem ->
                                            count.value +=i
                                            if(i>0) listCards.add(tCardsItem)
                                            if(i<0) listCards.remove(tCardsItem)
//                                    Log.d("count", "CardRow: ${listCards}")
                                            if(count.intValue == 3){
                                                cardViewModel.addCard(
                                                    TarotCards(
                                                        title = title.toString(),
                                                        description1 = listCards[0].description,
                                                        image1 = listCards[0].image,
                                                        name1 = listCards[0].name,
                                                        description2 = listCards[1].description,
                                                        image2 = listCards[1].image,
                                                        name2 = listCards[1].name,
                                                        description3 = listCards[2].description,
                                                        image3 = listCards[2].image,
                                                        name3 = listCards[2].name
                                                    )
                                                )
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

    }
}

@Composable
fun CardRow(card: TCardsItem, onCLickEvent: (Int,TCardsItem) -> Unit) {
    val selected = rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        onClick = {
            selected.value = !selected.value
            if(selected.value){
                onCLickEvent(1,card)
            } else {
                onCLickEvent(-1,card)
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

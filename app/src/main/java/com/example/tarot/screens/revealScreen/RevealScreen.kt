package com.example.tarot.screens.revealScreen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.tarot.navigation.TarotScreens
import com.example.tarot.screens.cardScreen.CardViewModel
import com.example.tarot.utils.Constants

@Composable
fun RevealScreen(navController: NavHostController, cardViewModel: CardViewModel) {
    BackHandler {
        navController.navigate(TarotScreens.HomeScreen.name)
    }
    val listOfCards = cardViewModel.cardList.collectAsState().value
    if (listOfCards.isNotEmpty()){
        Log.d("TAGG", "RevealScreen: ${listOfCards.last().title}")
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .size(200.dp)
                        .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp)),
                ){
                AsyncImage(
                    model = Constants.BASE_URL + listOfCards.last().image1,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit,
                )
                }
                Card(
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .size(200.dp)
                        .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp)),
                ){
                AsyncImage(
                    model = Constants.BASE_URL + listOfCards.last().image2,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit,
                )
                }
                Card(
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)
                        .size(200.dp)
                        .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp)),
                ){
                AsyncImage(
                    model = Constants.BASE_URL + listOfCards.last().image3,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit,
                )
                }
            }
        }

    }
}
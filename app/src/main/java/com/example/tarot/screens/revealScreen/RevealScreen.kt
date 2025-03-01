package com.example.tarot.screens.revealScreen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.tarot.navigation.TarotScreens
import com.example.tarot.screens.cardScreen.CardViewModel
import com.example.tarot.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevealScreen(navController: NavHostController, cardViewModel: CardViewModel = hiltViewModel()) {
    BackHandler {
        navController.navigate(TarotScreens.HomeScreen.name)
    }

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
                            navController.navigate(TarotScreens.HomeScreen.name)
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

        val listOfCards = cardViewModel.cardList.collectAsState().value
        if (listOfCards.isNotEmpty()){
            Log.d("TAGG", "RevealScreen: ${listOfCards.last().title}")
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Card 1
                    ShowCard(
                        image = listOfCards.last().image1,
                        name = listOfCards.last().name1,
                        description = listOfCards.last().description1
                    )

                    //Card 2
                    ShowCard(
                        image = listOfCards.last().image2,
                        name = listOfCards.last().name2,
                        description = listOfCards.last().description2
                    )

                    //Card 3
                    ShowCard(
                        image = listOfCards.last().image3,
                        name = listOfCards.last().name3,
                        description = listOfCards.last().description3
                    )

                }
            }
        }
    }

}

@Composable
fun ShowCard(
    image: String,
    name: String,
    description: String
){
    val scrollState = rememberScrollState()
    Card(
        onClick = {},
        modifier = Modifier
            .padding(5.dp)
            .height(200.dp)
            .fillMaxWidth()
            .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp)),
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = Constants.BASE_URL + image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight(),
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(8.dp)
            ) {
                Text(
                    name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    description,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }

}
package com.example.tarot.screens.pastReadingScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tarot.screens.cardScreen.CardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastReadingScreen(cardViewModel: CardViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "TarotVerse",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Cyan
                )
            )
        }
    ){innerPadding ->
        val listOfCards = cardViewModel.cardList.collectAsState().value
        if(listOfCards.isNotEmpty()){
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn {
                        items(listOfCards){card->
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                shape = RoundedCornerShape(15.dp),
                                shadowElevation = 5.dp,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier.weight(0.9f)
                                    ){
                                        Text(
                                            "Question: ${card.title}",
                                            modifier = Modifier.padding(5.dp),
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Row{
                                            Text(
                                                "Selected Cards: ",
                                                modifier = Modifier.padding(5.dp),
                                                style = MaterialTheme.typography.labelLarge
                                            )
                                            Column{
                                                Text(
                                                    card.name1,
                                                    modifier = Modifier
                                                        .padding(3.dp)
                                                        .padding(top = 2.dp),
                                                    style = MaterialTheme.typography.labelLarge
                                                )
                                                Text(
                                                    card.name2,
                                                    modifier = Modifier.padding(3.dp),
                                                    style = MaterialTheme.typography.labelLarge
                                                )
                                                Text(
                                                    card.name3,
                                                    modifier = Modifier.padding(3.dp),
                                                    style = MaterialTheme.typography.labelLarge
                                                )
                                            }
                                        }
                                    }

                                    IconButton(
                                        onClick = {
                                            cardViewModel.removeCard(card)
                                        },
                                        modifier = Modifier.weight(0.1f)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete Icon"
                                        )
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
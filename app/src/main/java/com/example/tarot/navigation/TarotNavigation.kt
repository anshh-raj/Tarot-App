package com.example.tarot.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tarot.screens.cardScreen.CardScreen
import com.example.tarot.screens.cardScreen.CardViewModel
import com.example.tarot.screens.homeScreen.HomeScreen

@Composable
fun TarotNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TarotScreens.HomeScreen.name){
        composable(TarotScreens.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(TarotScreens.CardScreen.name){
            val cardViewModel = hiltViewModel<CardViewModel>()
            CardScreen(navController, cardViewModel)
        }
    }
}
package com.example.tarot.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tarot.screens.cardScreen.CardScreen
import com.example.tarot.screens.cardScreen.CardViewModel
import com.example.tarot.screens.homeScreen.HomeScreen
import com.example.tarot.screens.revealScreen.RevealScreen

@Composable
fun TarotNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TarotScreens.HomeScreen.name){
        composable(TarotScreens.HomeScreen.name){
            HomeScreen(navController)
        }
        composable(TarotScreens.CardScreen.name + "/{title}",
            arguments = listOf(navArgument(name = "title"){type = NavType.StringType})
        ){backStackEntry ->
            val cardViewModel = hiltViewModel<CardViewModel>()
            CardScreen(navController, cardViewModel, title = backStackEntry.arguments?.getString("title"))
        }
        composable(TarotScreens.RevealScreen.name){
            val cardViewModel = hiltViewModel<CardViewModel>()
            RevealScreen(navController, cardViewModel)
        }
    }
}
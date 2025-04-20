package com.example.newsnow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsnow.ui.Screens.HomeScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) {
//inside this we will define the home screen but for that we will need home screen which we will generate4
            //We will have a package inside ui named screen and make all the screens there
            HomeScreen()
            //if someone  trie to come to nav host and tries to navigate to Routes.HOME_SCREEN then they will be shown this home screen composable
        }

    }
}
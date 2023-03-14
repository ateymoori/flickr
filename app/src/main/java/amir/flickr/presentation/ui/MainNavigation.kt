package amir.flickr.presentation.ui

import amir.flickr.presentation.ui.screens.home.HomeScreen
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavigation(
    navController: NavHostController,
    startDestination: String = "home"
) {
    val activity = LocalContext.current as Activity
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            BackHandler(true) {
                activity.finish()
            }
            HomeScreen()
        }
    }
}
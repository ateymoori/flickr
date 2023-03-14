package amir.flickr.presentation

import amir.flickr.presentation.ui.MainNavigation
import amir.flickr.presentation.ui.theme.FlickrTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrTheme {
                val navController = rememberNavController()
                MainNavigation(
                    navController = navController
                )
            }
        }
    }
}
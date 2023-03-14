package amir.flickr.presentation.ui.screens.home

import amir.flickr.data.models.Photo
import amir.flickr.presentation.ui.base.Failed
import amir.flickr.presentation.ui.base.InitialState
import amir.flickr.presentation.ui.base.Loaded
import amir.flickr.presentation.ui.base.Loading
import amir.flickr.presentation.ui.components.ListItem
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun HomeScreen(
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val textState = remember { mutableStateOf(TextFieldValue()) }

    val focusManager = LocalFocusManager.current

    Column(Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text("Enter word") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
            )

            //debounce
            LaunchedEffect(key1 = textState.value.text) {
                if (textState.value.text.isBlank()) return@LaunchedEffect
                delay(1500)
                homeViewModel.dispatch(HomeContract.HomeAction.Search(textState.value.text))
                focusManager.clearFocus()
            }
        }
        TracksList(homeViewModel)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TracksList(homeViewModel: HomeViewModel) {
    val lazyListState = rememberLazyListState()
    val viewState = homeViewModel.viewState.collectAsState()

    var photos = listOf<Photo>()

    when (val results = viewState.value.state) {
        is Failed -> {
            //show error message
        }
        InitialState -> {
        }
        is Loaded -> {
            if (results.data.isEmpty()) {
                NoResultsFoundScreen()
            } else {
                photos = results.data
            }
        }
        Loading -> {
            //show loading
        }
    }

    LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
        itemsIndexed(photos, key = { _: Int, photo: Photo ->
            photo.id
        }) { index: Int, photo: Photo ->
            ListItem(Modifier.animateItemPlacement(), photo)
            if (index == photos.lastIndex) {
                homeViewModel.dispatch(HomeContract.HomeAction.ScrolledToEndOfTheList)
            }
        }
    }

}

@Composable
fun NoResultsFoundScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Error icon",
            tint = Color.Gray,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "No results found",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }

}














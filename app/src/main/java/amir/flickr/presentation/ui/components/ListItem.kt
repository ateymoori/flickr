package amir.flickr.presentation.ui.components

import amir.flickr.data.models.Photo
import amir.flickr.data.models.getPhotoUrl
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ListItem(modifier: Modifier, photo: Photo) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, color = Color.LightGray, shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = photo.getPhotoUrl(),
                contentDescription = "photo",
                modifier = Modifier
                    .size(96.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Text(
                text = photo.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .widthIn(max = 240.dp)
            )
        }
    }
}
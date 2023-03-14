package amir.flickr.presentation.ui.components

import amir.flickr.data.models.Photo
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test

class ListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun listItemDisplaysCorrectly() {
        val photo = Photo(
            id = "1",
            owner = "me",
            secret = "test",
            server = "server",
            farm = 1,
            title = "title",
            isPublic = 1,
            isFriend = 2,
            isFamily = 5

        )

        composeTestRule.setContent {
            ListItem(photo = photo, modifier = Modifier.padding())
        }
        composeTestRule.onNodeWithContentDescription("photo").assertIsDisplayed()
    }
}

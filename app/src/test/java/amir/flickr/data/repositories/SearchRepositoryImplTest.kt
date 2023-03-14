package amir.flickr.data.repositories

import amir.flickr.data.api.RestAPI
import amir.flickr.data.models.Photo
import amir.flickr.data.models.Photos
import amir.flickr.data.models.PhotosResponse
import amir.flickr.domain.repositories.SearchRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class SearchRepositoryImplTest {

    private lateinit var repository: SearchRepository
    private lateinit var restApi: RestAPI

    @Before
    fun setUp() {
        restApi = mock(RestAPI::class.java)
        repository = SearchRepositoryImpl(restApi)
    }

    @Test
    fun `search should return photos from rest api`() = runBlocking {
        val expectedPhotos = PhotosResponse(Photos(
            photoList = listOf(
                Photo(
                    id = "1",
                    owner = "2",
                    secret = "3",
                    server = "4",
                    farm = 123,
                    title = "title",
                    isPublic = 1,
                    isFriend = 2,
                    isFamily = 3
                )
            ), pages = 0, perPage = 0, total = 0, page = 0
        ))
        `when`(restApi.searchPhoto(text = "test", page = 1)).thenReturn(expectedPhotos)

        val result = repository.search("test", 1)

        assertEquals(expectedPhotos.photos, result)
    }

}

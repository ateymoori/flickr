package amir.flickr.domain.usecase

import amir.flickr.data.models.Photo
import amir.flickr.data.models.Photos
import amir.flickr.domain.repositories.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*

class SearchUseCaseTest {
    private val searchRepository = mock<SearchRepository>()
    private val searchUseCase = SearchUseCase(searchRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `invoke should call searchRepository with correct parameters`() = runTest {
        val query = "test"
        val currentPage = 1
        val expectedPhotos = listOf(
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
        )
        val photos = Photos(page = 1, pages = 2, perPage = 10, total = 100, photoList = expectedPhotos)
        `when`(searchRepository.search(query, currentPage)).thenReturn(photos)

        val actualPhotos = searchUseCase.invoke(query, currentPage)

        verify(searchRepository).search(query, currentPage)
        assertEquals(expectedPhotos, actualPhotos.photoList)
    }
}
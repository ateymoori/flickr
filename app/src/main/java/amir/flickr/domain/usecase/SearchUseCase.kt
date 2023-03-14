package amir.flickr.domain.usecase

import amir.flickr.data.models.Photos
import amir.flickr.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend fun invoke(query: String, currentPage: Int): Photos {
        return searchRepository.search(query = query, currentPage = currentPage)
    }
}
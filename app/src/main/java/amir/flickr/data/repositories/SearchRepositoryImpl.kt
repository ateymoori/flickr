package amir.flickr.data.repositories

import amir.flickr.data.api.RestAPI
import amir.flickr.data.models.Photos
import amir.flickr.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val restApi: RestAPI
) : SearchRepository {
    override suspend fun search(query: String, currentPage: Int): Photos {
        return restApi.searchPhoto(text = query, page = currentPage).photos
    }
}


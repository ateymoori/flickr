package amir.flickr.domain.repositories

import amir.flickr.data.models.Photos

interface SearchRepository {
    suspend fun search(query: String, currentPage: Int): Photos
}
package amir.flickr.data.api

import amir.flickr.data.models.PhotosResponse
import retrofit2.http.*

interface RestAPI {

    companion object {
        const val BASE_API_URL = "https://api.flickr.com/"
    }

    @GET("services/rest")
    suspend fun searchPhoto(
        @Query("api_key") api_key: String = "5a2cc90782760b3a6b3eca570dfaf5c3",
        @Query("method") method: String = "flickr.photos.search",
        @Query("text") text: String = "",
        @Query("format") format: String = "json",
        @Query("per_page") per_page: Int = 10,
        @Query("page") page: Int = 0,
        @Query("nojsoncallback") nojsoncallback: String = "1"
    ): PhotosResponse

}



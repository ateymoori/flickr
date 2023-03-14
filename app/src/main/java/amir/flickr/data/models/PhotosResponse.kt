package amir.flickr.data.models

import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("photos") val photos: Photos
)

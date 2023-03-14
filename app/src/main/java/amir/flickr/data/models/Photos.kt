package amir.flickr.data.models

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("photo") val photoList: List<Photo>
)
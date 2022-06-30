package com.random.animequote.model

import com.google.gson.annotations.SerializedName

data class ImageQueryObject (

    val results: List<ImagesResults>?

)

data class ImagesResults(
    @SerializedName("url")
    val url: String,

    @SerializedName("thumbnail")
    val thumbnail: String,
)


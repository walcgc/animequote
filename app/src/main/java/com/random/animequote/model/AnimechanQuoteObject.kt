package com.random.animequote.model

import com.google.gson.annotations.SerializedName

data class AnimechanQuoteObject(
    @SerializedName("anime")
    val anime: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("quote")
    val quote: String
)

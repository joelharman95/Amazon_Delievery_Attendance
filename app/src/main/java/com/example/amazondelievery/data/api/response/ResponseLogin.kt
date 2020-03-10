package com.example.amazondelievery.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("iserror") val isError: Boolean? = null,
    val message: String? = null,
    val content: String? = null
)